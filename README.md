zk-viewstack
============

this demo shows a missing feature from flex: Viewstack!

	<viewStack show="@bind(vm.vs)">
		<stack id="list">
			...
		</stack>
		<stack id="detail">
			...
		</stack>
	</viewStack>

building that zul layout you can update the vm.vs property with the stack id name the selected stack will be shown.

running demo:

run java Application.java, then go to: http://127.0.0.1:8080/testVM.zul


 <b> Steps for adding into your zk project</b>

	add Viewstack & Stack classes
	
	add addons xml definitions:
		src/main/webapp/WEB-INF/addon/lang-addon-stack.xml
		src/main/webapp/WEB-INF/addon/lang-addon-viewstack.xml
	
	use them in zk.xml
	
		<language-config>
			<addon-uri>/WEB-INF/addon/lang-addon-viewStack.xml</addon-uri>
			<addon-uri>/WEB-INF/addon/lang-addon-stack.xml</addon-uri>
		</language-config>
	
