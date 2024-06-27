package Container;

import Model.Driven_Adapter;
import Model.Driving_Adapter;
import Model.InputPort;
import Model.OutputPort;

public class Factory {
	
	public InputPort criarPortaEntrada() {
		return new Driving_Adapter();
	}
	
	public OutputPort criarPortaExterna() {
		return new Driven_Adapter();
	}
}
