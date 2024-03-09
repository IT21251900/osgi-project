package adventure.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceRegistration;

import adventure.publisher.AdventurPackagesInterface;
import adventure.publisher.AdventurePackagesImp;

import org.osgi.framework.BundleContext;

public class AdventurePackagesActivator implements BundleActivator {

	ServiceRegistration pubServiceReg;

	// The start Adventure Publisher
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Adventure Publisher has Started.");

		AdventurPackagesInterface AdventurePublisher = new AdventurePackagesImp();

		// Register service 
		pubServiceReg = bundleContext.registerService(AdventurPackagesInterface.class.getName(),
				AdventurePublisher, null);
	}

	// The stop Adventure Publisher
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Adventure Publisher has Stoped.");

		// Unregister service 
		pubServiceReg.unregister();
	}

}
