

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AppModelAbstract implements SDModel {
	
	private Object distance=null;
	private Object duration=null;
	private Object exhalation=null;
	
	@Override
	public int getDistance() {
		return (int) this.distance;
	}

	@Override
	public int getDuration() {
		return (int) this.duration;
	}

	@Override
	public int getExhalation() {
		return (int) this.exhalation;
	}

	
	public PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
	
	public void reset() {
		this.distance =null;
		this.duration =null;
		this.exhalation =null;
	}

	
	@Override
	public void setInteraction(int distance, int duration, int exhalation_level) {
		Object oldDistance = this.distance;
		Object oldDuration = this.duration;
		Object oldExhalation = this.exhalation;
		this.distance = distance;
		this.duration = duration;
		this.exhalation = exhalation_level;
		propertyChangeSupport.firePropertyChange(SDModel.Distance, oldDistance, this.distance);
		propertyChangeSupport.firePropertyChange(SDModel.Duration, oldDuration, this.duration);
		propertyChangeSupport.firePropertyChange(SDModel.ExhalationLevel, oldExhalation, this.exhalation );
		this.isSafe();
		reset();
	}

}
