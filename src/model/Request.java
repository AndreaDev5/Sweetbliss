package model;

public class Request {
    private int id;
    private int userId;
    private int productId;
    private String mensaje;
    private String fecha;

    public Request() {}

    public Request(int id, int userId, int productId, String mensaje, String fecha) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
