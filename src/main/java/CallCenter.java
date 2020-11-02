public class CallCenter {
    private volatile boolean isOperating;

    public CallCenter() {
        isOperating = true;
    }

    public boolean isOperating() {
        return isOperating;
    }

    public void setOperating(boolean operating) {
        isOperating = operating;
    }
}
