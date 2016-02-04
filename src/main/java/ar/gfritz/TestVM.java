package ar.gfritz;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class TestVM {

	private String name = "pepe";

	String vs = "list";
	
	public String getVs() {
		return vs;
	}

	public void setVs(String vs) {
		this.vs = vs;
	}

	@WireVariable
	TestService testService;

	@Command
	@NotifyChange("name")
	public void ook() {
		name = testService.getName();
	}

	@Command
	@NotifyChange("vs")
	public void showDetail() {
		vs="detail";
	}

	@Command
	@NotifyChange("vs")
	public void showList() {
		vs="list";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
