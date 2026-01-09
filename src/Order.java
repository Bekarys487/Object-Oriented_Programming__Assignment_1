public class Order {
    protected int orderId;
    protected String customerName;
    protected double total;
    protected String status;

    public Order(int orderId, String customerName, double total, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        setTotal(total);
        this.status = status;
    }

    public Order() {
        this.orderId = 0;
        this.customerName = "Unknown";
        this.total = 0.0;
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(int orderId) {
        if (orderId > 0) {
            this.orderId = orderId;
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        }
    }

    public void setTotal(double total) {
        if (total >= 0) {
            this.total = total;
        }
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    public void addAmount(double amount) {
        if (amount > 0) {
            total += amount;
        }
    }

    public void complete() {
        status = "Completed";
    }

    public void cancel() {
        status = "Cancelled";
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}