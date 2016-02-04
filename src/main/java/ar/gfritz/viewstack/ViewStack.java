package ar.gfritz.viewstack;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

/**
 * Implementación de ViewStack, simil a flex. <br>
 * Ejemplo de uso: <br>
 * <br>
 * zul:<br>
 * <code>
 * &lt;viewStack id="viewStack"&gt;<br>
 * 		&lt;stack id="listado"&gt;<br>
 * 			...componentes del listado...<br>
 * 		&lt;/stack&gt;<br>
 * 		&lt;stack id="detalle"&gt;<br>
 * 			...componentes del detalle...<br>
 * 		&lt;/stack&gt;<br>
 * &lt;/viewStack&gt;<br>
 * </code><br>
 * <br>
 * java:<br>
 * <code>
 *
 * @Wire<br> ViewStack viewStack;<br><br> viewStack.mostrar("detalle");<br>
 *           </code>
 *
 *
 */
public class ViewStack extends Div implements AfterCompose {

	private static final String _100 = "100%";

	private static final long serialVersionUID = 5699188493163951650L;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	Map<String, Stack> map = new HashMap<String, Stack>();

	private String actualStack = "";

	public ViewStack() {
		setWidth(_100);
		setHeight(_100);
	}

	@Override
	public void afterCompose() {
		Boolean primero = Boolean.TRUE;
		for (Component c : this.getChildren()) {
			if (c instanceof Stack) {
				Stack stack = (Stack) c;

				validaStack(stack);

				stack.setVisible(primero);
				if (primero) {
					primero = Boolean.FALSE;
				}
				map.put(stack.getId(), stack);
			} else {
				throw new ViewStackException("ViewStack solo puede tener tags de Stack, este componente esta mal ubicado: " + c.toString());
			}
		}
	}

	/**
	 * Valida el stack
	 *
	 * @param stack
	 */
	private void validaStack(Stack stack) {
		if (stack == null) {
			throw new ViewStackException("El stack no puede ser null");
		}
		if (StringUtils.isBlank(stack.getId())) {
			throw new ViewStackException("El stack no tiene ID!");
		}
		if (map.containsKey(stack.getId())) {
			throw new ViewStackException("El ID del stack " + stack.getId() + " esta repetido!");
		}
	}

	/**
	 * Muestra el stack pedido, eligiendolo por su ID
	 *
	 * @param idAMostrar
	 */
	public void show(String idAMostrar) {
		for (String key : map.keySet()) {
			if (key.equals(idAMostrar)) {
				map.get(key).setVisible(Boolean.TRUE);
				actualStack = key;
			} else {
				map.get(key).setVisible(Boolean.FALSE);
			}
		}
	}
	
	public void setShow(String id) {
		show(id);
	}

	/**
	 * Agrega un nuevo stack
	 *
	 * @param c
	 * @throws ViewStackException
	 */
	public void addStack(Component c) {
		Stack stack = new Stack();
		c.setParent(stack);
		addStack(stack);
	}

	/**
	 * Agrega un nuevo stack
	 *
	 * @param stack
	 * @throws ViewStackException
	 */
	public void addStack(Stack stack) {
		validaStack(stack);
		map.put(stack.getId(), stack);
		stack.setParent(this);
	}

	public class ViewStackException extends NestedRuntimeException {
		private static final long serialVersionUID = 5641549263295019001L;

		public ViewStackException(String msg) {
			super(msg);
		}
	}

	public String getActualStack() {
		return actualStack;
	}

	public void setActualStack(String actualStack) {
		this.actualStack = actualStack;
	}
}
