package managesys.configuration;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "datasource.hibernate")
public class HibernateProps extends HibernateProperties {

    private String dialect;
    private String showSql;
    private String formatSql;
    private String nonContextualCreation = "false";

    public Map<String, Object> getProperties() {
        Map<String, String> props = new HashMap<String, String>();
        props.put(AvailableSettings.DIALECT, dialect);
        props.put(AvailableSettings.SHOW_SQL, showSql);
        props.put(AvailableSettings.FORMAT_SQL, formatSql);
        props.put(AvailableSettings.NON_CONTEXTUAL_LOB_CREATION, nonContextualCreation);
        return determineHibernateProperties(props, new HibernateSettings());
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    public String getNonContextualCreation() {
        return nonContextualCreation;
    }

    public void setNonContextualCreation(String nonContextualCreation) {
        this.nonContextualCreation = nonContextualCreation;
    }

}
