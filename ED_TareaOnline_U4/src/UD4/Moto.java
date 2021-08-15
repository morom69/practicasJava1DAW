package UD4;

/**
 *
 * @author Miguel Moro
 * @version 2.0
 */
public class Moto {

    private String modelo; 
    private int numeroRuedas; // número de ruedas de la moto
    private int cilindrada; // cilindrada de la moto en centímetros cúbicos
    private int velocidadMaxima; // velocidad máxima que es capaz de alcanzar la moto
    private int peso; // peso de la moto en kilogramos
    private String color; // color de la moto, en caso de que tenga varios se anotará el predominante
    private int precio; // precio de la moto en euros
    private int stock;//Cantidad de motos de las que se disponen

    /**
     *
     */
    public Moto() {
    }

    public Moto(String modelo, String color, int stock) {
        this.modelo = modelo;
        this.color = color;
        this.stock = stock;
    }

    public Moto(String modelo, int numeroRuedas, int cilindrada, int velocidadMaxima, int peso, String color, int precio, int stock) {
        this.modelo = modelo;
        this.numeroRuedas = numeroRuedas;
        this.cilindrada = cilindrada;
        this.velocidadMaxima = velocidadMaxima;
        this.peso = peso;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return the numeroRuedas
     */
    public int getNumeroRuedas() {
        return numeroRuedas;
    }

    /**
     * @return the cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }

    /**
     * @return the velocidadMaxima
     */
    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @param numeroRuedas the numeroRuedas to set
     */
    public void setNumeroRuedas(int numeroRuedas) {
        this.numeroRuedas = numeroRuedas;
    }

    /**
     * @param cilindrada the cilindrada to set
     */
    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    /**
     * @param velocidadMaxima the velocidadMaxima to set
     */
    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * El método comprar actualiza el stock incrementando la cantidad del stock
     * actual (recuperada con getStock()) con la cantidad pasada como parámetro
     * mediante el uso del método setter setStock()
     *
     * @param cantidad: recibe un entero que será la cantidad a sumar a nuestro stock existente
     * @throws Exception: lanza una excepción si la cantidad pasada como parámetro es negativa
     * @return: no se utiliza return al devolver el método void
     */
    public void comprar(int cantidad) throws Exception {
        if (cantidad < 0) {
            throw new Exception("No se puede comprar un nº negativo de motos");
        }
        setStock(getStock() + cantidad);
    }

}
