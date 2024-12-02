package PublImpacto.SistemaGestion.model.db;

public class Configuracion {
	 private String dbUrl;
	    private String dbUser;
	    private String dbPassword;
	    private String emailServer;

	    public Configuracion(String dbUrl, String dbUser, String dbPassword, String emailServer) {
	        this.dbUrl = dbUrl;
	        this.dbUser = dbUser;
	        this.dbPassword = dbPassword;
	        this.emailServer = emailServer;
	    }

	    public String getDbUrl() {
	        return dbUrl;
	    }

	    public void setDbUrl(String dbUrl) {
	        this.dbUrl = dbUrl;
	    }

	    public String getDbUser() {
	        return dbUser;
	    }

	    public void setDbUser(String dbUser) {
	        this.dbUser = dbUser;
	    }

	    public String getDbPassword() {
	        return dbPassword;
	    }

	    public void setDbPassword(String dbPassword) {
	        this.dbPassword = dbPassword;
	    }

	    public String getEmailServer() {
	        return emailServer;
	    }

	    public void setEmailServer(String emailServer) {
	        this.emailServer = emailServer;
	    }
}
