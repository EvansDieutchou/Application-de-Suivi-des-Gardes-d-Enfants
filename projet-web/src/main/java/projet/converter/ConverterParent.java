package projet.converter;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import projet.jsf.data.Parent;

@Named
@RequestScoped
public class ConverterParent implements Converter<Parent> {

	//-------
	// Actions
	//-------

	@SuppressWarnings("unchecked")
	@Override
	public Parent getAsObject(FacesContext context, UIComponent uic, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		List<Parent> items = null;
		for (UIComponent c : uic.getChildren()) {
			if (c instanceof UISelectItems) {
				items = (List<Parent>) ((UISelectItems) c).getValue();
				break;
			}
		}

		var id = Integer.valueOf(value);
		for (Parent item : items) {
			if (item.getId().equals(id)) {
				return item;
				
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Parent item) {

		if (item == null) {
			return "";
		}
		return String.valueOf(item.getId());
	}
}
