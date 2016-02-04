package ar.gfritz.viewstack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zul.Vlayout;

/**
 * Elemento hijo del {@link ViewStack}
 *
 * @author gfritz
 *
 */
public class Stack extends Vlayout {

	private static final String APP_STAGE = "app-stage";
	private static final String _100 = "100%";

	private static final long serialVersionUID = -7002602742956016834L;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public Stack() {
		setWidth(_100);
		setHeight(_100);
		setAction("show: slideDown({duration: 600}); hide: slideUp({duration: 600})");
		setSclass(APP_STAGE);
	}
}
