package Herança;

class Automotores {  //Superclasse 'Automotores' com atributos em comum
	//atributos em comum da superclasse
    private String modelo;
    private String anoFab;
    private String montadora;
    private String cor;
    private int km;
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
    public String insert() { //método que gera o comando insert para criar a tabela no SQL
            return "INSERT INTO Automotores (modelo, anoFab, montadora, cor, km) VALUES ('" 
                    + modelo + "', '" + anoFab + "', '" + montadora + "', '" + cor + "', " + km + ");";
        
    }
}

class Automovel extends Automotores { //subclasse 'Automovel'
	//atributos exclusivos da subclasse 'Automovel'
    private int passageiros;
    private String freio;
    private String airbag;
    private String descricao;

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

	@Override
    public String insert() { //método que gera o comando insert para criar a tabela no SQL. @Overrid para garantir que os métodos estão corretos
        return "INSERT INTO Automovel (modelo, anoFab, montadora, cor, km, passageiros, freio, airbag) VALUES ('" 
                + getModelo() + "', '" + getAnoFab() + "', '" + getMontadora() + "', '" + getCor() + "', " + getKm() + ", " 
                + passageiros + ", '" + freio + "', '" + airbag + "');";
    }
}

class Motocicleta extends Automotores { //subclasse 'Motocicleta'
	//atributos exclusivos da subclasse 'Motocicleta'
    private int cilindradas;
    private float torque;
    private String descricao;

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

   @Override
    public String insert() { //método que gera o comando insert para criar a tabela no SQL
        return "INSERT INTO Motocicleta (modelo, anoFab, montadora, cor, km, cilindradas, torque) VALUES ('" 
                + getModelo() + "', '" + getAnoFab() + "', '" + getMontadora() + "', '" + getCor() + "', " + getKm() + ", " 
                + cilindradas + ", " + torque + ");";
    }
}

class Caminhao extends Automotores { //subclasse 'Caminhao'
	//atributos exclusivos da subclasse 'Caminhao'
    private int eixos;
    private double peso;
    private String descricao;

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
    
   @Override 
    public String insert() { //método que gera o comando insert para criar a tabela no SQL
        return "INSERT INTO Caminhao (modelo, anoFab, montadora, cor, km, eixos, peso) VALUES ('" 
                + getModelo() + "', '" + getAnoFab() + "', '" + getMontadora() + "', '" + getCor() + "', " + getKm() + ", " 
                + eixos + ", " + peso + ");";
    }
}

class Bicicleta { //classe 'Bicicleta'
	//atributos da classe 'Bicicleta'
    private String modelo;
    private String anoFab;
    private String marca;
    private String cor;
    private String material;
    private int marchas;
    private String descricao;
    
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
    
    public String insert() { //método que gera o comando insert para criar a tabela no SQL
        return "INSERT INTO Bicicleta (modelo, anoFab, marca, cor, material, marchas) VALUES ('" 
                + modelo + "', '" + anoFab + "', '" + marca + "', '" + cor + "', '" + material + "', " + marchas + ");";
    }
}

class Skate { //classe 'Skate'
	//atributos da classe 'Skate'
    private String modelo;
    private String anoFab;
    private String marca;
    private String cor;
    private String rodas;
    private String descricao;

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

    public String insert() {  //método que gera o comando insert para criar a tabela no SQL
        return "INSERT INTO Skate (modelo, anoFab, marca, cor, rodas) VALUES ('" 
                + modelo + "', '" + anoFab + "', '" + marca + "', '" + cor + "', '" + rodas + "');";
    }
}

public class SISTEMA_VEICULOS { //classe principal
    public static void main(String[] args) { // Método main do projeto para impressão da descrição
        try{ // try-catch para verificar se existe uma exceção
        Automovel a1 = new Automovel("Sedan", "2014", "Honda", "Prata", 24000, 2, "Disco", "duplo frontal"); // Instância a1 com os valores dos parâmetros do 'Automovel'
        System.out.println(a1.getDescricao()); // Acessa a descrição pelo 'getDescrição()'
        System.out.println(" Insert: " + a1.insert()); //Instância a1 para criar a tabela com o insert
        System.out.println(" ");
        } catch (IllegalArgumentException e) {                           // Se algum dos parâmetros está incorreto; 'IllegalArgumentException e' para o getMessage acessar o 'IllegalArgumentException' referente ao parâmetro incorreto
        System.out.println(" Ocorreu o seguinte erro: " + e.getMessage()); // Se tiver incorreto retorna o 'IllegalArgumentException' referente ao parâmetro incorreto
        System.out.println(" ");
    }
        
        try {
        Motocicleta m1 = new Motocicleta("scooter", "2010", "Honda", "Preta", 6000, 149, 1.3f); // Instância m1 com os valores dos parâmetros do 'Motocicleta'
        System.out.println(m1.getDescricao()); // Acessa a descrição pelo 'getDescrição()'
        System.out.println(" Insert: " + m1.insert()); //Instância m1 para criar a tabela com o insert
        System.out.println(" ");
        } catch (IllegalArgumentException e) {                           // Se algum dos parâmetros está incorreto; 'IllegalArgumentException e' para o getMessage acessar o 'IllegalArgumentException' referente ao parâmetro incorreto
        System.out.println(" Ocorreu o seguinte erro: " + e.getMessage()); // Se tiver incorreto retorna o 'IllegalArgumentException' referente ao parâmetro incorreto
        System.out.println(" ");
    }
        
        try {
        Caminhao c1 = new Caminhao("Bitrem", "2020", "Facchini", "Preta", 0, 8, 65.5); // Instância c1 com os valores dos parâmetros do 'Caminhao'
        System.out.println(c1.getDescricao()); // Acessa a descrição pelo 'getDescrição()'
        System.out.println(" Insert: " + c1.insert()); //Instância c1 para criar a tabela com o insert
        System.out.println(" ");
        } catch (IllegalArgumentException e) {                           // Se algum dos parâmetros está incorreto; 'IllegalArgumentException e' para o getMessage acessar o 'IllegalArgumentException' referente ao parâmetro incorreto
            System.out.println(" Ocorreu o seguinte erro: " + e.getMessage()); // Se tiver incorreto retorna o 'IllegalArgumentException' referente ao parâmetro incorreto
            System.out.println(" ");
     }
        try {
        Bicicleta b1 = new Bicicleta("Caloi", "2024", "Caloi", "Branca", "Carbono", 24); // Instância b1 com os valores dos parâmetros do 'Bicicleta'
        System.out.println(b1.desc()); // Acessa a descrição pelo 'getDescrição()'
        System.out.println(" Insert: " + b1.insert()); //Instância b1 para criar a tabela com o insert
        System.out.println(" ");
        } catch (IllegalArgumentException e) {                           // Se algum dos parâmetros está incorreto; 'IllegalArgumentException e' para o getMessage acessar o 'IllegalArgumentException' referente ao parâmetro incorreto
            System.out.println(" Ocorreu o seguinte erro: " + e.getMessage()); // Se tiver incorreto retorna o 'IllegalArgumentException' referente ao parâmetro incorreto
            System.out.println(" ");
     }
        try {
        Skate s1 = new Skate("Semi-profissional", "2018", "Almost", "Azul", "Roda 54"); // Instância s1 com os valores dos parâmetros do 'Skate'
        System.out.println(s1.desc()); // Acessa a descrição pelo 'getDescrição()'
        System.out.println(" Insert: " + s1.insert()); //Instância s1 para criar a tabela com o insert
        System.out.println(" ");
        } catch (IllegalArgumentException e) {                           // Se algum dos parâmetros está incorreto; 'IllegalArgumentException e' para o getMessage acessar o 'IllegalArgumentException' referente ao parâmetro incorreto
            System.out.println(" Ocorreu o seguinte erro: " + e.getMessage()); // Se tiver incorreto retorna o 'IllegalArgumentException' referente ao parâmetro incorreto
     }
 }
}

