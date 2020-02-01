package com.blackfriday.api.DAOs;

public enum QueryEnums {
	INSERT_INTO("INSERT INTO"),
	SELECT("SELECT"),
	TABLE_PRODUCT("product"),
	TABLE_USER("user");
	
	
	private final String text;

    /**
     * @param text
     */
	QueryEnums(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
	
}
