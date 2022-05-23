package com.example.intl_phone_numbers.dialects;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.NULL, "null");
        registerHibernateType(Types.NULL, "null");
        // other data types

//        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
//        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
//        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
//        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
//        registerFunction("lower", new StandardSQLFunction("lower", StringType.INSTANCE));
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getDropForeignKeyString() {
        return "";
    }

    @Override
    public String getAddForeignKeyConstraintString(String cn, String[] fk, String t, String[] pk, boolean rpk) {
        return "";
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String query, boolean hasOffset) {
        return query + (hasOffset ? " limit ? offset ?" : " limit ?");
//        return new StringBuilder(query.length() + 20).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?")
//                .toString();
    }

    @Override
    public String getAddColumnString() {
        return "add column";
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }
}
