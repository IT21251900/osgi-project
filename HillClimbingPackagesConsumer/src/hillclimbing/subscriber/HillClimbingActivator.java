package hillclimbing.subscriber;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import hillclimbing.publisher.HillClimbingPackagesInterface;


public class HillClimbingActivator implements BundleActivator {

	// Declare service reference variable
	ServiceReference hillClimbingServiceReference;
	
	// Declare scanner object for user input
	Scanner scan = new Scanner(System.in);

	// Start method for bundle activation
	public void start(BundleContext bundleContext) throws Exception {

		// Declare and initialize variables
		int publisherType, selected, type, exType;
		double totalPrice = 0, discount = 0, extraPrice = 0;
		float discountPercentage = (float) 0.05;
		ArrayList<String> selectedPlaces = new ArrayList<String>();

		hillClimbingServiceReference= bundleContext.getServiceReference(HillClimbingPackagesInterface.class.getName());
		HillClimbingPackagesInterface hillClimbingPublisher = (HillClimbingPackagesInterface) bundleContext.getService(hillClimbingServiceReference);

		System.out.println("\n======================= Welcome to HillClimbing Packages Consumer! =======================\n");
		System.out.println("Press 1 to continue with hillClimbing packages.");
		System.out.println("Press 0 to exit.");

		try {
			System.out.print("Enter your choice: ");
			publisherType = scan.nextInt();
			System.out.println("_____________________________________________________________________________\n");
			
		if (publisherType == 1) {

				System.out.println("\n------------------------- Categories Of Hill Climbing ---------------------------");
				System.out.println("1. Day Mountaineering");
				System.out.println("2. Overnight Mountaineering");
				System.out.println("3. Waterfall Climbing");
				System.out.println("4. Alpine Climbing\n");
				System.out.println("****************************");
				System.out.println();

				System.out.println("(To exit and get the total, press 0.)");
				System.out.print("Enter the category of hill climbing you want to select: ");
				type = scan.nextInt();
				
				
				while (type != 0) {
					
					// Get user input for extra items selection
					System.out.print("\nDo you want any extra items for the hill climbing? (Y/N): ");
					char isExtra = scan.next().charAt(0);
						
					// If user wants extra items
					if (isExtra == 'Y' || isExtra == 'y') {

						// Display extra items and their prices
							System.out.println("\n------------------------------ Extra Stuff ------------------------------");
							System.out.println("1.  Climbing pack \t\t LKR 2500.00");
							System.out.println("2.  Helmet \t\t LKR 1000.00");
							System.out.println("3.  Tent \t\t LKR 3000.00");
							System.out.println("4.  Ropes(dry preferred)\t\t LKR 500.00");
							System.out.println("****************************\n");
						    System.out.println("\n(Enter -1 to exit)");

							do {
							System.out.print("Enter the number of the extra item you want to select: ");
							exType = scan.nextInt();
	
							// Calculate and add extra item price to total price
							switch (exType) {
								case 1:
									extraPrice = 2500;
									break;
								case 2:
									extraPrice = 1000;
									break;
								case 3:
									extraPrice = 3000;
									break;
								case 4:
									extraPrice = 500;
									break;
								default:
									extraPrice = 0;
							}
							
							totalPrice += extraPrice;
							
						} while (exType != -1);

						System.out.println("_____________________________________________________________________________\n");
					}

					System.out.println("\n------------------------------ Hiking Places ------------------------------\n");
					// Display hill climbing packages under user's selected category
					hillClimbingPublisher.displayHillClimbingsByCategory(type);
			
					
					System.out.println("\n(To quit the current category, enter -1.)");
					System.out.print("Enter the hiking place you want to select");
					selected = scan.nextInt();
					
				
					// Loop through selected hiking places
					while (selected != -1) {
						// Calculate and add hiking place price to total price
						totalPrice += hillClimbingPublisher.getHillClimbingsPrice(type, selected);
						selectedPlaces.add(hillClimbingPublisher.getHillClimbingCategory(type, selected));
						
						System.out.print("Enter the hiking place you want to select: ");
						selected = scan.nextInt();
					}

					System.out.print("\nPress 0 to get the balance and buy hill climbing packages.\n");
					System.out.print("Enter the category of hill climbing you want to purchase next: ");
					type = scan.nextInt();
				}

				
				discount = totalPrice * discountPercentage;
				double finalPrice = totalPrice - discount;

				System.out.println("\n------------------------------ Summary Report ------------------------------");
				System.out.println("Selected Places:");
				for (String place : selectedPlaces) {
				    System.out.println("- " + place);
				}
				System.out.printf("\n%-20s %s", "Total Price:", "LKR " + String.format("%.2f", totalPrice));
				System.out.printf("\n%-20s %s", "Discount:", "LKR " + String.format("%.2f", discount));
				System.out.printf("\n%-20s %s", "Final Price:", "LKR " + String.format("%.2f", finalPrice));
				System.out.println("\n\n===========================================================================");

			}else if(publisherType == 0) {
				// If user wants to exit, stop bundle activation
				this.stop(bundleContext);
			}
				
		} catch (InputMismatchException e) {
			System.out.println("Invalid input! Please enter an integer.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Stop method for bundle deactivation
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Hill Climbing Subscriber has stopped.");
		bundleContext.ungetService(hillClimbingServiceReference);
		
	}

}