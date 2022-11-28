package hashMap;
import arraylist.Car;
import java.util.Set;
import java.util.List;

public interface CarMap {
	
	public void put(CarOwner key,Car value);
	
	public Car get(CarOwner key);
	
	public Set<CarOwner> keySet();
	
	public List<Car> values();
	
	public boolean remove(CarOwner key);
	
	public int size();
	
	public void clear();

}
