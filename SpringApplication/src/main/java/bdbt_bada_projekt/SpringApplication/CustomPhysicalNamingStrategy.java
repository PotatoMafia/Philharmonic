package bdbt_bada_projekt.SpringApplication;

import org.hibernate.boot.model.naming.Identifier;;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return Identifier.toIdentifier(quote(firstLetterToUppercase(name.getText())));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return Identifier.toIdentifier(quote(firstLetterToUppercase(camelCaseToUnderscores(name.getText()))));
    }

    private String quote(String name) {
        return "\"" + name + "\"";
    }

    private String firstLetterToUppercase(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private String camelCaseToUnderscores(String name) {
        return name.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase();
    }
}

