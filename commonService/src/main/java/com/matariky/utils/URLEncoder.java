package com.matariky.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.BitSet;

/**
 * URL Code, Data Content Type is Application/X-WWW-Form-Urlencoded.
 *
 * <pre>
 * 1. characters "a"-"z", "a"-"z", "0"-"9", ".", "*", "_" Will not be code;
 * 2. Convert the space to%20;
 * 3. Convert the non -Text Content to "%xy". XY is the two hexadecimal values;
 * 4. Place & amp; symbols between each name = value pair.
 * </pre>
 *
 *
 */
public class URLEncoder implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Default {@Link urlencoder} <br>
	 * The default Code is code for the URI path, and the definition is as follows:
	 *
	 * <pre>
	 * pchar = unreserved (not processed) / PCT-ENCODED / SUB-DELIMS
	 * unreserved = alpha / digit / "- " /". " /" _ " /" ~ "
	 * sub-delims = "! /" $ " /" & amp; " /" '" /" (" /" (" /") " /" * " /"+" /", " /"; " /" = "
	 * </pre>
	 */
	public static final URLEncoder DEFAULT = createDefault();

	/**
	 * {@Link Urlencoder} used for the query statement
	 * CODE device is code for the URI path, and the definition is as follows:
	 *
	 * <pre>
	 * 0x20 '' = "'+'
	 * 0x2a, 0x2d, 0x2e, 0x30 to 0x39, 0x41 to 0x5a, 0x61 to 0x7a as-in
	 * '*', '-', '.', '0' to '9', 'a' to 'z', '_', 'a' to 'z' also '=' and '& amp; Code
	 * Other CODE is %NN form
	 * </pre>
	 *
	 * Detail see: https://www.w3.org/tr/html5/Forms.html#Application/X-www- Form-
	 * Urlencoding-Algorithmmmmmmmmmmmmmmmmmmmmmmmm
	 */
	public static final URLEncoder QUERY = createQuery();

	/**
	 * Create default {@link urlencoder} <br>
	 * The default Code is code for the URI path, and the definition is as follows:
	 *
	 * <pre>
	 * pchar = unreserved (not processed) / PCT-ENCODED / SUB-DELIMS
	 * unreserved = alpha / digit / "- " /". " /" _ " /" ~ "
	 * sub-delims = "! /" $ " /" & amp; " /" '" /" (" /" (" /") " /" * " /"+" /", " /"; " /" = "
	 * </pre>
	 *
	 * @Return {@Link Urlencoder}
	 */
	public static URLEncoder createDefault() {
		final URLEncoder encoder = new URLEncoder();
		encoder.addSafeCharacter('-');
		encoder.addSafeCharacter('.');
		encoder.addSafeCharacter('_');
		encoder.addSafeCharacter('~');
		// Add the sub-delims
		encoder.addSafeCharacter('!');
		encoder.addSafeCharacter('$');
		encoder.addSafeCharacter('&');
		encoder.addSafeCharacter('\'');
		encoder.addSafeCharacter('(');
		encoder.addSafeCharacter(')');
		encoder.addSafeCharacter('*');
		encoder.addSafeCharacter('+');
		encoder.addSafeCharacter(',');
		encoder.addSafeCharacter(';');
		encoder.addSafeCharacter('=');
		// Add the remaining literals
		encoder.addSafeCharacter(':');
		encoder.addSafeCharacter('@');
		// Add '/' so it isn't encoded when we encode a path
		encoder.addSafeCharacter('/');

		return encoder;
	}

	/**
	 * Create for the query statement {@Link urlencoder} <br>
	 * CODE device is code for the URI path, and the definition is as follows:
	 *
	 * <pre>
	 * 0x20 '' = "'+'
	 * 0x2a, 0x2d, 0x2e, 0x30 to 0x39, 0x41 to 0x5a, 0x61 to 0x7a as-in
	 * '*', '-', '.', '0' to '9', 'a' to 'z', '_', 'a' to 'z' also '=' and '& amp; Code
	 * Other CODE is %NN form
	 * </pre>
	 *
	 * Detail see: https://www.w3.org/tr/html5/Forms.html#Application/X-www- Form-
	 * Urlencoding-Algorithmmmmmmmmmmmmmmmmmmmmmmmm
	 *
	 * @Return {@Link Urlencoder}
	 */
	public static URLEncoder createQuery() {
		final URLEncoder encoder = new URLEncoder();
		// Special encoding for space
		encoder.setEncodeSpaceAsPlus(true);
		// Alpha and digit are safe by default
		// Add the other permitted characters
		encoder.addSafeCharacter('*');
		encoder.addSafeCharacter('-');
		encoder.addSafeCharacter('.');
		encoder.addSafeCharacter('_');
		encoder.addSafeCharacter('=');
		encoder.addSafeCharacter('&');

		return encoder;
	}
	// ---------------------------------------------------------------------------------------------
	// Static method end

	/** Storage safety Code */
	private final BitSet safeCharacters;
	/** Wether Code Space+ */
	private boolean encodeSpaceAsPlus = false;

	/**
	 * Construction <br>
	 *
	 * [A-Za-Z0-9] The default is not CODE
	 */
	public URLEncoder() {
		this(new BitSet(256));

		for (char i = 'a'; i <= 'z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = '0'; i <= '9'; i++) {
			addSafeCharacter(i);
		}
	}

	/**
	 * Constructor
	 *
	 * @param Safecharacters Safety characters, safe characters are not code
	 */
	private URLEncoder(BitSet safeCharacters) {
		this.safeCharacters = safeCharacters;
	}

	/**
	 * Increase security characters <br>
	 * Safe characters are not code
	 *
	 * @param C character
	 */
	public void addSafeCharacter(char c) {
		safeCharacters.set(c);
	}

	/**
	 * Remove safe character <br>
	 * Safe characters are not code
	 *
	 * @param C character
	 */
	public void removeSafeCharacter(char c) {
		safeCharacters.clear(c);
	}

	/**
	 * WETHER to bring the space code to+
	 *
	 * @param encodespaceasplus Wether gives the space code to+
	 */
	public void setEncodeSpaceAsPlus(boolean encodeSpaceAsPlus) {
		this.encodeSpaceAsPlus = encodeSpaceAsPlus;
	}

	/**
	 * Code in the string in the URL into%form
	 *
	 * @param Path    requires the code string of code
	 * @param Charset Code
	 *
	 * @Return code string
	 */
	public String encode(String path, Charset charset) {

		int maxBytesPerChar = 10;
		final StringBuilder rewrittenPath = new StringBuilder(path.length());
		ByteArrayOutputStream buf = new ByteArrayOutputStream(maxBytesPerChar);
		OutputStreamWriter writer = new OutputStreamWriter(buf, charset);

		int c;
		for (int i = 0; i < path.length(); i++) {
			c = path.charAt(i);
			if (safeCharacters.get(c)) {
				rewrittenPath.append((char) c);
			} else if (encodeSpaceAsPlus && c == ' ') {
				// Solocent processing
				rewrittenPath.append('+');
			} else {
				// convert to external encoding before hex conversion
				try {
					writer.write((char) c);
					writer.flush();
				} catch (IOException e) {
					buf.reset();
					continue;
				}

				byte[] ba = buf.toByteArray();
				for (int j = 0; j < ba.length; j++) {
					// Converting each byte in the buffer
					byte toEncode = ba[j];
					rewrittenPath.append('%');
					HexUtil.appendHex(rewrittenPath, toEncode, false);
				}
				buf.reset();
			}
		}
		return rewrittenPath.toString();
	}
}
