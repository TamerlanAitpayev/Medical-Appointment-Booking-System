package Config;

public class ClinicConfig {
    private static ClinicConfig instance;
    private String clinicName = "City Medical Center";

    private ClinicConfig() {}

    public static synchronized ClinicConfig getInstance() {
        if (instance == null) {
            instance = new ClinicConfig();
        }
        return instance;
    }

    public String getClinicName() { return clinicName; }
}