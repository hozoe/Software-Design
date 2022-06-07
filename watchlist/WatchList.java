package assignment5;

import java.util.*;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	private Optional<Watchable> aLastWatched = Optional.empty();
	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		aName = pName;
	}

	public Command setNameCommand(String pName){
		return new Command() {
			String aName = getName();
			@Override
			public void execute() {
				setName(pName);
			}

			@Override
			public void undo() {
				setName(aName);
			}

			@Override
			public void redo() {
				setName(pName);
			}
		};
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;
		aList.add(pWatchable);
	}

	public Command addWatchableCommand(Watchable pWatchable){
		return new Command() {

			@Override
			public void execute() {
				addWatchable(pWatchable);
			}

			@Override
			public void undo() {
				if (aList.contains(pWatchable) && aList.get(aList.size()-1).equals(pWatchable)){
					aList.remove(aList.size()-1);
				}
			}

			@Override
			public void redo() {
				addWatchable(pWatchable);
			}
		};
	}
	/**
	 * Retrieves and removes the Watchable at the specified index.
	 * 
	 * @param pIndex
	 *            the position of the Watchable to remove
	 * @pre pIndex < getTotalCount() && pIndex >= 0
	 */
	public Watchable removeWatchable(int pIndex) {
		assert pIndex < aList.size() && pIndex >= 0;
		if (aNext > pIndex) {
			aNext--;
		}
		return aList.remove(pIndex);
	}

	public Command removeWatchableCommand(int pIndex){
		return new Command() {
			Watchable w = aList.get(pIndex);
			@Override
			public void execute() {
				removeWatchable(pIndex);
			}

			@Override
			public void undo() {
				addWatchable(w);
			}

			@Override
			public void redo() {
				removeWatchable(pIndex);
			}
		};
	}
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Watchable next = aList.get(aNext);
		aNext++;
		if (aNext >= aList.size()) {
			aNext = 0;
		}
		return next;
	}

	public Command nextCommand(){
		return new Command() {
			int pNumber = getaNext();
			@Override
			public void execute() {
				next();
			}

			@Override
			public void undo() {
				aNext = pNumber;
			}

			@Override
			public void redo() {
				next();
			}
		};
	}
	@Override
	public void reset() {
		aNext = 0;
	}

	public int getaNext(){
		return this.aNext;
	}

	public Command resetCommand(){
		return new Command() {
			int pNumber = getaNext();
			@Override
			public void execute() {
				reset();
			}

			@Override
			public void undo() {
				aNext = pNumber;
			}

			@Override
			public void redo() {
				reset();
			}
		};
	}
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}

	public void setLastWatched(Watchable pWatchable){
		assert pWatchable!=null;
		this.aLastWatched = Optional.of(pWatchable);
	}
	public Watchable lastWatched(){
		assert this.aLastWatched.isPresent();
		return aLastWatched.get();
	}
}
