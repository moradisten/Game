package topos.structure;



/**
 * The class BasicPanel represents the panels in the game. Inside these panels we can find the elements.
 * 
 * @author Morad
 *
 */
public class BasicPanel implements Cloneable{

	
	public static final int HIDING_TIME = 5000;  // time the panel will be invisible after being shot
	
	private Position position;						// Position of the panel inside the scenry
	
	private boolean visibility;
	
	private long shotTime;							// The instant of time when the panel was shot
	
	
	
	public BasicPanel(int x, int y, boolean visible) {
		if((x < 0) || (y < 0)) 
				throw new IllegalArgumentException("Los valores de las posiciones no pueden ser negativos");
		
		this.position = new Position(x, y);
		this.visibility = visible;
		this.shotTime = 0;
	}
	

	public BasicPanel(int x, int y)  {	
		this(x, y, true); // visibility = true
	}
	
	
	public Position getPosition(){		
		return position;
	}
	
	public void setPosition(Position pos) throws CloneNotSupportedException {
		this.position = new Position(pos.clone());
	}
	
	public boolean isVisible() {
		return visibility;
	}
	
	
	protected void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	
	
	public boolean isHidden() {
		return !visibility;
	}
	

	/**
	 * This void methods, checks the visibility of the panel.
	 * If it's visible, the visibility will turn false and time will start running.
	 * This methods is used in the moment of the shot of a panel.
	 */
	public void strike() {
		if (isVisible()) {
			this.visibility = false;		
			shotTime = System.currentTimeMillis();
		}		
	}
	
	
	/**
	 * This check method turns the panel visible after the hiding time has passed.
	 */
	public void updateVisibility() {
		if (System.currentTimeMillis() - shotTime > HIDING_TIME)
			this.visibility = true;
	}

	

	
	public String getImagen() {
		return "imagenes/panel-basico.gif";
	}

	
	public int getPosicionX() {
		
		return position.getX();
	}

	
	public int getPosicionY() {
		
		return position.getY();
	}

	/** 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + (int) (shotTime ^ (shotTime >>> 32));
		result = prime * result + (visibility ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null && this != null) {
			return false;
		}
		if (!(obj instanceof BasicPanel)) {
			return false;
		}
		
		if (obj instanceof FlashPanel)
			return false;
		
		if (obj instanceof ResistantPanel)
			return false;
		
		if(obj instanceof RandomPanel)
			return false;
		
		BasicPanel other = (BasicPanel) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		if (shotTime != other.shotTime) {
			return false;
		}

		return true;
	}

	private BasicPanel copy() {
		Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("Can't be cloned");
        }
        return (BasicPanel) obj;
	}

	@Override
	public BasicPanel clone() throws CloneNotSupportedException {
		BasicPanel copy = copy();
		copy.position = this.position.clone();
		copy.visibility = this.visibility;
		return copy;
	}
	
}


