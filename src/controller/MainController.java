package controller;

import entity.PaymentRequest;
import exception.OperationNotPermittedException;
import model.Catalogue;
import model.Product;
import model.TransactionState;

// vivek.g@zeta.tech	
public class MainController {

	InventoryManager icontroller;	// manages the inventory of the ingredients
	TransactionManager tmanager;	// manages the current transactions being taken care of by the system across various clients
	MachineController mcontroller;	// controls the machines needed to vend the ingredients
	AdminController acontroller;	// admin functionality goes here --> add inventory etc-
	PaymentController pcontroller;	// payment processing
	PriceManager pmanager;
	Catalogue catalogue;			// centralized catalog of all products that can be vended
	
	public MainController () {
		
		catalogue = new Catalogue();
		catalogue.initalizeDefaultProducts();

		icontroller = new InventoryManager();
		mcontroller = new MachineController(icontroller);
		pmanager = new PriceManager();
		acontroller = new AdminController(icontroller, pmanager);
		acontroller.initialize();
	}
	
	// vends the product
	public void process (int client, Product product) throws OperationNotPermittedException {
		
		// check if valid request, i.e., payment is done
		if (!tmanager.isValid(client, TransactionState.PROCESS))
			throw new OperationNotPermittedException("request out of order");
		
		if (!icontroller.isAvailable(product.ingredients)) {
			System.out.println("Out of ingredients! Please come back later!");
		}
		
		// initiate payment process
		mcontroller.process(product);
		
		// update state
		tmanager.cleanUp(client);
	}
	
	private void validateRequest (int client, TransactionState type) throws OperationNotPermittedException {
		
		if (!tmanager.isValid(client, type))
			throw new OperationNotPermittedException("request out of order");
		
		// update the current state
		tmanager.update(client, type);
	}
	
	// describes the catalog
	public void describe (int client) throws OperationNotPermittedException {
		
		validateRequest(client, TransactionState.DESCRIBE);
		catalogue.describe();
	}
	
	// process payment for client
	public int pay (PaymentRequest request) throws OperationNotPermittedException {
		
		validateRequest(request.clientId, TransactionState.PAY);
		int resp = pcontroller.process(request.clientId, pmanager.get(request.productId, request.size), request.tender);
		
		if (resp >= 0)
			tmanager.update(request.clientId, TransactionState.PAY_SUCCESS);
		
		return resp;
	}
	
	/**
	 * cancel the transaction and refund any amount tendered so far
	 * 
	 * @param client
	 * @return
	 */
	public int cancel (int client) throws OperationNotPermittedException {
		
		validateRequest(client, TransactionState.CANCEL);
		return pcontroller.cancelTransaction(client);
	}
}
