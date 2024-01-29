package begin.flywayspringmaven.support.sql;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class AppMySQL5Dialect extends MySQL5Dialect {
    public AppMySQL5Dialect() {
        super();
        registerFunction("REGEXP", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 REGEXP ?2"));
    }
}
