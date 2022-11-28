package hashMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arraylist.Car;

public class CarHashMap implements CarMap {
	private int size = 0;
	private final int INITIAL = 16;
	private Entry[] array = new Entry[INITIAL];
	private final double LOAD_FACTOR = 0.75;

	@Override
	public void put(CarOwner key, Car value) {
		if (size >= LOAD_FACTOR * array.length) {
			increaseArray();
		}
		boolean added = put(key, value, array);
		if (added) {
			size++;
		}
	}

	private boolean put(CarOwner key, Car value, Entry[] dst) {
		int index = Math.abs(key.hashCode() % dst.length);
		if (dst[index] == null) {
			dst[index] = new Entry(key, value, null);
			return true;
		}

		if (dst[index] != null) {
			Entry currentEntry = dst[index];
			while (true) {
				if (currentEntry.key.equals(key)) {
					currentEntry.value = value;
					return false;
				}
				if (currentEntry.next == null) {
					currentEntry.next = new Entry(key, value, null);
					return true;
				}
				currentEntry = currentEntry.next;
			}
		}
		return false;
	}

	@Override
	public Car get(CarOwner key) {
		int index = Math.abs(key.hashCode() % array.length);

		Entry entry = array[index];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
			entry = entry.next;
		}

		return null;
	}

	@Override
	public Set<CarOwner> keySet() {
		Set<CarOwner> result = new HashSet<CarOwner>();

		for (Entry entry : array) {
			Entry existedElement = entry;
			while (existedElement != null) {
				result.add(existedElement.key);
				existedElement = existedElement.next;
			}

		}

		return result;
	}

	@Override
	public List<Car> values() {
		List<Car> result = new ArrayList<Car>();

		for (Entry entry : array) {
			Entry existedElement = entry;
			while (existedElement != null) {
				result.add(existedElement.value);
				existedElement = existedElement.next;
			}

		}

		return result;
	}

	@Override
	public boolean remove(CarOwner key) {
		int index = Math.abs(key.hashCode() % array.length);
		if (array[index] != null) {
			Entry secondLast = array[index];
			Entry last = secondLast.next;
			if (secondLast.key.equals(key)) {
				array[index] = last;
				size--;
				return true;
			}
			while (last != null) {
				if (last.key.equals(key)) {
					secondLast.next = last.next;
					size--;
					return true;
				} else {
					secondLast = last;
					last = last.next;
				}
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		array = new Entry[INITIAL];

	}

	private void increaseArray() {
		Entry[] newArray = new Entry[array.length * 2];
		for (Entry entry : array) {
			Entry current = entry;
			while (current != null) {
				put(entry.key, entry.value, newArray);
				current = current.next;
			}
		}
		array = newArray;
	}

	private class Entry {
		private CarOwner key;
		private Car value;
		private Entry next;

		public Entry(CarOwner key, Car value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

	}

}
