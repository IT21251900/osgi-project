package safari.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceRegistration;

import safari.publisher.SafariPublisherImp;
import safari.publisher.SafariPublisherInterface;

import org.osgi.framework.BundleContext;

public class SafariActivator implements BundleActivator {

	ServiceRegistration publisherServiceReg;

	// The start Adventure Publisher
	public void start(BundleContext bundleContext) throws Exception {
//		System.out.println("The Safari Publisher has begun.");

		SafariPublisherInterface SafariPublisher = new SafariPublisherImp();

		// Register service to the service registry(bind the service)
		publisherServiceReg = bundleContext.registerService(SafariPublisherInterface.class.getName(),
				SafariPublisher, null);
	}

	// The stop Adventure Publisher
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Publisher for Safari has stopped.");

		// Unregister service from the service registry
		publisherServiceReg.unregister();
	}

}
