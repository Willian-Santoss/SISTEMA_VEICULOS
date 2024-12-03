package Sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class Automotores {  //Superclasse 'Automotores' com atributos em comum
	//atributos em comum da superclasse
    protected String modelo;
    protected String anoFab;
    protected String montadora;
    protected String cor;
    protected int km;
    private String descricao;


    public Automotores(String modelo, String anoFab, String montadora, String cor, int km) { //contrutor da superclasse
    	//encapsulamento da superclasse
        setModelo(modelo);
        setAnoFab(anoFab);
        setMontadora(montadora);
        setCor(cor);
        setKm(km);
        
        this.descricao = desc(); //método para atualizar a descrição
    }

    public void setModelo(String modelo) { //método para validar o valor de modelo
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode estar vazio.");
        }
        this.modelo = modelo; //inicializa 'modelo'
    }

    public void setAnoFab(String anoFab) { //método para validar o valor de ano de fabricação
        if (anoFab == null || !anoFab.matches("\\d{4}")) {
            throw new IllegalArgumentException("Ano de fabricação deve ser um ano válido (ex: 2024).");
        }
        this.anoFab = anoFab; //inicializa 'anoFab'
    }

    public void setMontadora(String montadora) { //método para validar o valor de montadora
        if (montadora == null || montadora.isEmpty()) {
            throw new IllegalArgumentException("Montadora não pode estar vazia.");
        }
        this.montadora = montadora; //inicializa 'montadora'
    }

    public void setCor(String cor) { //método para validar o valor de cor
        if (cor == null || cor.isEmpty()) {
            throw new IllegalArgumentException("Cor não pode estar vazia.");
        }
        this.cor = cor; //inicializa 'cor'
    }

    public void setKm(int km) { //método para validar o valor de kilometragem
        if (km < 0) {
            throw new IllegalArgumentException("Kilometragem não pode ser negativa.");
        }
        this.km = km; //inicializa 'km'
    }
    //getters para chamar os valores dos atributos privados
    public String getModelo() {
        return modelo;
    }

    public String getAnoFab() {
        return anoFab;
    }

    public String getMontadora() {
        return montadora;
    }

    public String getCor() {
        return cor;
    }

    public int getKm() {
        return km;
    }

    public String desc() { //método para retornar a descrição detalhada dos automotores
        return " Modelo: " + modelo + "\n Ano de fabricação: " + anoFab + "\n Montadora: " + montadora + "\n Cor: " + cor + "\n Kilometragem (odômetro): " + km + " km rodados";
    }

    public String getDescricao() { //getter para chamar a descrição
        return descricao;
    }
    
 // Método para enviar a descrição para uma tabela no SQL
    public void enviarDescricaoParaSQL() {
        String sql = "INSERT INTO Automotores (modelo, anoFab, montadora, cor, km, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modelo);
            pstmt.setString(2, anoFab);
            pstmt.setString(3, montadora);
            pstmt.setString(4, cor);
            pstmt.setInt(5, km);
            pstmt.setString(6, descricao);

            pstmt.executeUpdate();

            System.out.println("Descrição enviada para o banco de dados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar a descrição para o banco de dados.");
        }
    }
}

/**
 * Representa um automóvel com atributos específicos. 
 * Herda da classe Automotores. 
 */
class Automovel extends Automotores { //subclasse 'Automovel'
	//atributos exclusivos da subclasse 'Automovel'
    private int passageiros;
    private String freio;
    private String airbag;
    private String descricao;

    /** 
     * Construtor para criar um automóvel. 
     * 
     * @param modelo O modelo do automóvel.
     * @param anoFab O ano de fabricação do automóvel. 
     * @param montadora A montadora do automóvel. 
     * @param cor A cor do automóvel. 
     * @param km A quilometragem do automóvel. 
     * @param passageiros O número de passageiros do automóvel. 
     * @param freio O tipo de freio do automóvel. 
     * @param airbag O tipo de airbag do automóvel. 
     */
    public Automovel(String modelo, String anoFab, String montadora, String cor, int km, int passageiros, String freio, String airbag) { //construtor da subclasse 'Automovel' herdando os parametros da super classe com 'super'
        super(modelo, anoFab, montadora, cor, km);
        //encapsulamento da subclasse
        setPassageiros(passageiros);
        setFreio(freio);
        setAirbag(airbag);
        
        this.descricao = desc();
    }

    //setters para validar os atributos exclusivos do construtor da subclasse 'Automovel'
    public void setPassageiros(int passageiros) {
        if (passageiros <= 0) {
            throw new IllegalArgumentException("Número de passageiros deve ser positivo.");
        }
        this.passageiros = passageiros;
    }

    public void setFreio(String freio) {
        if (freio == null || freio.isEmpty()) {
            throw new IllegalArgumentException("Tipo de freio não pode estar vazio.");
        }
        this.freio = freio;
    }

    public void setAirbag(String airbag) {
        if (airbag == null || airbag.isEmpty()) {
            throw new IllegalArgumentException("Tipo de airbag não pode estar vazio.");
        }
        this.airbag = airbag;
    }

    //getters para chamar os valores dos atributos privados
    public int getPassageiros() {
        return passageiros;
    }

    public String getFreio() {
        return freio;
    }

    public String getAirbag() {
        return airbag;
    }

    @Override  //método para retornar a descrição detalhada do automovel com os atributos da descrição da superclasse. @Overrid para garantir que os métodos estão corretos
    public String desc() {
        return super.desc() + "\n Quantidade máxima de passageiros: " + passageiros + "\n Tipo de freio: " + freio + "\n Airbag: " + airbag;  
    }

    public String getDescricao() {
		return descricao;
	}
    
    /** 
     * Envia a descrição do automóvel para a tabela correspondente no banco de dados. 
     */
    public void enviarDescricaoParaSQL() {
        String sql = "INSERT INTO Automovel (modelo, anoFab, montadora, cor, km, passageiros, freio, airbag, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, modelo);
                pstmt.setString(2, anoFab);
                pstmt.setString(3, montadora);
                pstmt.setString(4, cor);
                pstmt.setInt(5, km);
                pstmt.setInt(6, passageiros);
                pstmt.setString(7, freio);
                pstmt.setString(8, airbag);
                pstmt.setString(9, descricao);

                pstmt.executeUpdate();

                System.out.println("Descrição enviada para o banco de dados com sucesso!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao enviar a descrição para o banco de dados.");
            }
        }
    }


    /** 
     * Representa uma motocicleta com atributos específicos. 
     * Herda da classe Automotores. 
     */
    class Motocicleta extends Automotores { //subclasse 'Motocicleta'
	//atributos exclusivos da subclasse 'Motocicleta'
    private int cilindradas;
    private float torque;
    private String descricao;

    /** 
     * Construtor para criar uma motocicleta. 
     * 
     * @param modelo O modelo da motocicleta. 
     * @param anoFab O ano de fabricação da motocicleta.
     * @param montadora A montadora da motocicleta.
     * @param cor A cor da motocicleta. 
     * @param km A quilometragem da motocicleta. 
     * @param cilindradas As cilindradas da motocicleta. 
     * @param torque O torque da motocicleta. 
     */
    
    public Motocicleta(String modelo, String anoFab, String montadora, String cor, int km, int cilindradas, float torque) { //construtor da subclasse 'Motocicleta' herdando os parametros da super classe com 'super'
        super(modelo, anoFab, montadora, cor, km);
        //encapsulamento da subclasse
        setCilindradas(cilindradas);
        setTorque(torque);
        
        this.descricao = desc();
    }
    //setters para validar os atributos exclusivos do construtor da subclasse 'Motocicleta'
    public void setCilindradas(int cilindradas) {
        if (cilindradas <= 0) {
            throw new IllegalArgumentException("Cilindradas devem ser um número positivo.");
        }
        this.cilindradas = cilindradas;
    }

    public void setTorque(float torque) {
        if (torque < 0) {
            throw new IllegalArgumentException("Torque não pode ser negativo.");
        }
        this.torque = torque;
    }
    //getters para chamar os valores dos atributos privados
    public int getCilindradas() {
        return cilindradas;
    }

    public float getTorque() {
        return torque;
    }

    @Override
    public String desc() { //método para retornar a descrição detalhada do motocicleta com os atributos da descrição da superclasse.
        return super.desc() + "\n Cilindradas: " + cilindradas + " cm³" + "\n Torque: " + torque + " kgf";
    }
    
    public String getDescricao() {
		return descricao;
	}

    /** 
     * Envia a descrição da motocicleta para a tabela correspondente no banco de dados. 
     */
      public void enviarDescricaoParaSQL() {
         String sql = "INSERT INTO Motocicleta (modelo, anoFab, montadora, cor, km, cilindradas, torque, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

         try (Connection conn = Conexao.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, modelo);
                pstmt.setString(2, anoFab);
                pstmt.setString(3, montadora);
                pstmt.setString(4, cor);
                pstmt.setInt(5, km);
                pstmt.setInt(6, cilindradas);
                pstmt.setFloat(7, torque);
                pstmt.setString(8, descricao);

                pstmt.executeUpdate();

                System.out.println("Descrição enviada para o banco de dados com sucesso!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao enviar a descrição para o banco de dados.");
            }
        }
    }

    /** 
     * Representa um caminhão com atributos específicos. 
     * Herda da classe Automotores. 
     */
    class Caminhao extends Automotores { //subclasse 'Caminhao'
	//atributos exclusivos da subclasse 'Caminhao'
    private int eixos;
    private double peso;
    private String descricao;

    /** 
     * Construtor para criar um caminhão. 
     * 
     * @param modelo O modelo do caminhão. 
     * @param anoFab O ano de fabricação do caminhão. 
     * @param montadora A montadora do caminhão.
     * @param cor A cor do caminhão. 
     * @param km A quilometragem do caminhão. 
     * @param eixos O número de eixos do caminhão. 
     * @param peso O peso bruto do caminhão. 
     */
    public Caminhao(String modelo, String anoFab, String montadora, String cor, int km, int eixos, double peso) { //construtor da subclasse 'Caminhao' herdando os parametros da super classe com 'super'
        super(modelo, anoFab, montadora, cor, km);
        //encapsulamento da subclasse
        setEixos(eixos);
        setPeso(peso);
        
        this.descricao = desc();
    }
    //setters para validar os atributos exclusivos do construtor da subclasse 'Caminhão'
    public void setEixos(int eixos) {
        if (eixos <= 0) {
            throw new IllegalArgumentException("Número de eixos deve ser positivo.");
        }
        this.eixos = eixos;
    }

    public void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("Peso não pode ser negativo.");
        }
        this.peso = peso;
    }
    //getters para chamar os valores dos atributos privados
    public int getEixos() {
        return eixos;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String desc() { //método para retornar a descrição detalhada do caminhão com os atributos da descrição da superclasse.
        return super.desc() + "\n Quantidade de eixos: " + eixos + "\n Peso bruto: " + peso + "t";
    }

    public String getDescricao() {
		return descricao;
	}
    
    /** 
     * Envia a descrição do caminhão para a tabela correspondente no banco de dados. 
     */
    public void enviarDescricaoParaSQL() {
        String sql = "INSERT INTO Caminhao (modelo, anoFab, montadora, cor, km, eixos, peso, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modelo);
            pstmt.setString(2, anoFab);
            pstmt.setString(3, montadora);
            pstmt.setString(4, cor);
            pstmt.setInt(5, km);
            pstmt.setInt(6, eixos);
            pstmt.setDouble(7, peso);
            pstmt.setString(8, descricao);

            pstmt.executeUpdate();

            System.out.println("Descrição enviada para o banco de dados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar a descrição para o banco de dados.");
        }
    }
}

    /** 
     * Representa uma bicicleta com atributos específicos. 
     */
    class Bicicleta { //classe 'Bicicleta'
	//atributos da classe 'Bicicleta'
    private String modelo;
    private String anoFab;
    private String marca;
    private String cor;
    private String material;
    private int marchas;
    private String descricao;
    
    /** 
     * Construtor para criar uma bicicleta. 
     * 
     * @param modelo O modelo da bicicleta. 
     * @param anoFab O ano de fabricação da bicicleta. 
     * @param marca A marca da bicicleta. 
     * @param cor A cor da bicicleta. 
     * @param material O material da bicicleta. 
     * @param marchas O número de marchas da bicicleta. 
     */
    public Bicicleta(String modelo, String anoFab, String marca, String cor, String material, int marchas) { //Construtor da classe 'Bicicleta'
    	//encapsulamento da classe
        setModelo(modelo);
        setAnoFab(anoFab);
        setMarca(marca);
        setCor(cor);
        setMaterial(material);
        setMarchas(marchas);
        
        this.descricao = desc();
    }
    //setters para validar os atributos exclusivos do construtor da classe 'Bicicleta'
    public void setModelo(String modelo) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode estar vazio.");
        }
        this.modelo = modelo;
    }

    public void setAnoFab(String anoFab) {
        if (anoFab == null || !anoFab.matches("\\d{4}")) {
            throw new IllegalArgumentException("Ano de fabricação deve ser um ano válido (ex: 2024).");
        }
        this.anoFab = anoFab;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("Marca não pode estar vazia.");
        }
        this.marca = marca;
    }

    public void setCor(String cor) {
        if (cor == null || cor.isEmpty()) {
            throw new IllegalArgumentException("Cor não pode estar vazia.");
        }
        this.cor = cor;
    }

    public void setMaterial(String material) {
        if (material == null || material.isEmpty()) {
            throw new IllegalArgumentException("Material não pode estar vazio.");
        }
        this.material = material;
    }

    public void setMarchas(int marchas) {
        if (marchas <= 0) {
            throw new IllegalArgumentException("Número de marchas deve ser positivo.");
        }
        this.marchas = marchas;
    }
    //getters para chamar os valores dos atributos privados
    public String getModelo() {
        return modelo;
    }

    public String getAnoFab() {
        return anoFab;
    }
    
    public String getMarca() {
        return marca;
    }

    public String getCor() {
        return cor;
    }

    public String getMaterial() {
        return material;
    }

    public int getMarchas() {
        return marchas;
    }

    public String desc() { //método para retornar a descrição detalhada da bicicleta.
        return " Modelo: " + modelo + "\n Ano de fabricação: " + anoFab + "\n Marca: " + marca + "\n Cor: " + cor + "\n Material: " + material + "\n Quantidade de marchas: " + marchas;
    }
    
    public String getDescricao() {
		return descricao;
	}
    
    /** 
     * Envia a descrição da bicicleta para a tabela correspondente no banco de dados. 
     */
    public void enviarDescricaoParaSQL() {
        String sql = "INSERT INTO Bicicleta (modelo, anoFab, marca, cor, material, marchas, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modelo);
            pstmt.setString(2, anoFab);
            pstmt.setString(3, marca);
            pstmt.setString(4, cor);
            pstmt.setString(5, material);
            pstmt.setInt(6, marchas);
            pstmt.setString(7, descricao);

            pstmt.executeUpdate();

            System.out.println("Descrição enviada para o banco de dados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar a descrição para o banco de dados.");
        }
    }
}

    /** 
     * Representa um skate com atributos específicos. 
     */
    class Skate { //classe 'Skate'
	//atributos da classe 'Skate'
    private String modelo;
    private String anoFab;
    private String marca;
    private String cor;
    private String rodas;
    private String descricao;
    
    /** 
     * Construtor para criar um skate. 
     * 
     * @param modelo O modelo do skate. 
     * @param anoFab O ano de fabricação do skate. 
     * @param marca A marca do skate. 
     * @param cor A cor do skate. 
     * @param rodas O tipo das rodas do skate. 
     */

    public Skate(String modelo, String anoFab, String marca, String cor, String rodas) { //Construtor da classe 'Skate'
    	//encapsulamento da classe
    	setModelo(modelo);
        setAnoFab(anoFab);
        setMarca(marca);
        setCor(cor);
        setRodas(rodas);
        
        this.descricao = desc();
    }
    //setters para validar os atributos exclusivos do construtor da classe 'Skate'
    public void setModelo(String modelo) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode estar vazio.");
        }
        this.modelo = modelo;
    }

    public void setAnoFab(String anoFab) {
        if (anoFab == null || !anoFab.matches("\\d{4}")) {
            throw new IllegalArgumentException("Ano de fabricação deve ser um ano válido (ex: 2024).");
        }
        this.anoFab = anoFab;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("Marca não pode estar vazia.");
        }
        this.marca = marca;
    }

    public void setCor(String cor) {
        if (cor == null || cor.isEmpty()) {
            throw new IllegalArgumentException("Cor não pode estar vazia.");
        }
        this.cor = cor;
    }

    public void setRodas(String rodas) {
        if (rodas == null || rodas.isEmpty()) {
            throw new IllegalArgumentException("Tipo das rodas não pode estar vazio.");
        }
        this.rodas = rodas;
    }
    //getters para chamar os valores dos atributos privados
    public String getModelo() {
        return modelo;
    }

    public String getAnoFab() {
        return anoFab;
    }
    
    public String getMarca() {
        return marca;
    }

    public String getCor() {
        return cor;
    }

    public String getRodas() {
        return rodas;
    }

    public String desc() { //método para retornar a descrição detalhada do skate.
        return " Modelo: " + modelo + "\n Ano de fabricação: " + anoFab + "\n Marca: " + marca + "\n Cor: " + cor + "\n Tipo das rodas: " + rodas;
    }
    
    public String getDescricao() {
		return descricao;
	}

    /** 
     * Envia a descrição do skate para a tabela correspondente no banco de dados. 
     */
    public void enviarDescricaoParaSQL() {
        String sql = "INSERT INTO Skate (modelo, anoFab, marca, cor, rodas, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modelo);
            pstmt.setString(2, anoFab);
            pstmt.setString(3, marca);
            pstmt.setString(4, cor);
            pstmt.setString(5, rodas);
            pstmt.setString(6, descricao);

            pstmt.executeUpdate();

            System.out.println("Descrição enviada para o banco de dados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar a descrição para o banco de dados.");
        }
    }
}

public class SISTEMA_VEICULOS { //classe principal
    public static void main(String[] args) { // Método main do projeto para impressão da descrição

        try { // try-catch para verificar se existe uma exceção
            Automovel a1 = new Automovel("Sedan", "2014", "Honda", "Prata", 24000, 2, "Disco", "duplo frontal"); // Instância a1 com os valores dos parâmetros do 'Automovel'
            System.out.println(a1.getDescricao()); // Acessa a descrição pelo 'getDescricao()'
            a1.enviarDescricaoParaSQL(); // Envia a descrição para o banco de dados
        } catch (IllegalArgumentException e) { // Se algum dos parâmetros está incorreto
            System.out.println("Ocorreu o seguinte erro: " + e.getMessage()); // Retorna a mensagem de erro
        }

        try {
            Motocicleta m1 = new Motocicleta("scooter", "2010", "Honda", "Preta", 6000, 149, 1.3f); // Instância m1 com os valores dos parâmetros do 'Motocicleta'
            System.out.println(m1.getDescricao()); // Acessa a descrição pelo 'getDescricao()'
            m1.enviarDescricaoParaSQL(); // Envia a descrição para o banco de dados
        } catch (IllegalArgumentException e) { // Se algum dos parâmetros está incorreto
            System.out.println("Ocorreu o seguinte erro: " + e.getMessage()); // Retorna a mensagem de erro
        }

        try {
            Caminhao c1 = new Caminhao("Bitrem", "2020", "Facchini", "Preta", 0, 8, 65.5); // Instância c1 com os valores dos parâmetros do 'Caminhao'
            System.out.println(c1.getDescricao()); // Acessa a descrição pelo 'getDescricao()'
            c1.enviarDescricaoParaSQL(); // Envia a descrição para o banco de dados
        } catch (IllegalArgumentException e) { // Se algum dos parâmetros está incorreto
            System.out.println("Ocorreu o seguinte erro: " + e.getMessage()); // Retorna a mensagem de erro
        }

        try {
            Bicicleta b1 = new Bicicleta("Caloi", "2024", "Caloi", "Branca", "Carbono", 24); // Instância b1 com os valores dos parâmetros do 'Bicicleta'
            System.out.println(b1.getDescricao()); // Acessa a descrição pelo 'getDescricao()'
            b1.enviarDescricaoParaSQL(); // Envia a descrição para o banco de dados
        } catch (IllegalArgumentException e) { // Se algum dos parâmetros está incorreto
            System.out.println("Ocorreu o seguinte erro: " + e.getMessage()); // Retorna a mensagem de erro
        }

        try {
            Skate s1 = new Skate("Semi-profissional", "2018", "Almost", "Azul", "Roda 54"); // Instância s1 com os valores dos parâmetros do 'Skate'
            System.out.println(s1.getDescricao()); // Acessa a descrição pelo 'getDescricao()'
            s1.enviarDescricaoParaSQL(); // Envia a descrição para o banco de dados
        } catch (IllegalArgumentException e) { // Se algum dos parâmetros está incorreto
            System.out.println("Ocorreu o seguinte erro: " + e.getMessage()); // Retorna a mensagem de erro
        }
    }
}
