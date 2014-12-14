package utils;

public final class Json
{
	/**
	 * object begins
	 */
	public static final String ob = "{";

	/**
	 * object ends
	 */
	public static final String oe = "}";

	/**
	 * array starts
	 */
	public static final String as = "[";

	/**
	 * array ends
	 */
	public static final String ad = "]";

	/**
	 * quotation mark - "
	 */
	public static final String qt = "\"";

	/**
	 * colon
	 */
	public static final String cn = ":";
	
	/**
	 * comma
	 */
	public static final String cm = ",";

	public static final void readJson(String message)
	{

	}

	public static final String writeJson()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(Json.ob);
		sb.append(Json.qt + "firstName" + Json.qt + Json.cn + Json.qt + "John" + Json.qt);
		
		sb.append(Json.oe);
		
		
		return sb.toString();
	}
	
	public static final String writeJson(int playerId)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(Json.ob);
		sb.append(Json.qt + "playerId" + Json.qt + Json.cn + Json.qt + playerId + Json.qt);
		
		sb.append(Json.oe);
		
		
		return sb.toString();
	}
}
