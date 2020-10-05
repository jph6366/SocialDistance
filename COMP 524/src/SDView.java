
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface SDView extends PropertyChangeListener{
	void propertyChange(PropertyChangeEvent evt);
}