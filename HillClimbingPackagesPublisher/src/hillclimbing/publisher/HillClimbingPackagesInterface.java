package hillclimbing.publisher;

public interface HillClimbingPackagesInterface {
	// Method declaration of displaying the places for the given category
		public void displayHillClimbingsByCategory(int num);

		// Method declaration of retrieving the prices
		public double getHillClimbingsPrice(int category, int place);

		// Method declaration of retrieving the places
		public String getHillClimbingCategory(int category, int place);
}
