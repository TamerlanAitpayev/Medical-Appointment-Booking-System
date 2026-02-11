package Components.Notifications;

public class NotificationComponent {
    public void send(String message) {
        System.out.println("[NotificationComponent] SMS/Email sent: " + message);
    }
}