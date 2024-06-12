package com.lacunasoftware.pkiexpress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrustServicesManager extends PkiExpressOperator {


	public TrustServicesManager(PkiExpressConfig config) {
		super(config);
	}

	public TrustServicesManager() throws IOException {
		this(new PkiExpressConfig());
	}

	public CheckServiceResult checkByCpf(String service, String cpf) throws IOException {
		if (Util.isNullOrEmpty(service)) {
			throw new RuntimeException("The provided service is not valid");
		}

		if (Util.isNullOrEmpty(cpf)) {
			throw new RuntimeException("The provided CPF is not valid");
		}

		List<String> args = new ArrayList<String>();
		args.add(service);

		// Add CPF
		args.add("--cpf");
		args.add(cpf);

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandCheckService, args);

		// Parse output and return result.
		CheckServiceResultModel model = parseOutput(response.getOutput()[0], CheckServiceResultModel.class);
		return new CheckServiceResult(model);
	}

	public CheckServiceResult checkByCnpj(String service, String cnpj) throws IOException {
		if (Util.isNullOrEmpty(service)) {
			throw new RuntimeException("The provided service is not valid");
		}

		if (Util.isNullOrEmpty(cnpj)) {
			throw new RuntimeException("The provided CNPJ is not valid");
		}

		List<String> args = new ArrayList<String>();
		args.add(service);

		// Add CNPJ
		args.add("--cnpj");
		args.add(cnpj);

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandCheckService, args);

		// Parse output and return result.
		CheckServiceResultModel model = parseOutput(response.getOutput()[0], CheckServiceResultModel.class);
		return new CheckServiceResult(model);
	}

	public List<TrustServiceInfo> discoverByCpf(String cpf) throws IOException {
		return discoverByCpf(cpf, false);
	}

	public List<TrustServiceInfo> discoverByCpf(String cpf, boolean throwExceptions) throws IOException {

		if (Util.isNullOrEmpty(cpf)) {
			throw new RuntimeException("The provided CPF is not valid");
		}

		List<String> args = new ArrayList<String>();

		// Add CPF
		args.add("--cpf");
		args.add(cpf);

		if (throwExceptions) {
			args.add("--throw");
		}

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandDiscoverServices, args);

		// Parse output and return result.
		DiscoverServicesResultModel model = parseOutput(response.getOutput()[0], DiscoverServicesResultModel.class);
		List<TrustServiceInfo> services = new ArrayList<TrustServiceInfo>();
		for (TrustServiceInfoModel serviceModel : model.getServices()) {
			services.add(new TrustServiceInfo(serviceModel));
		}
		return services;
	}

	public List<TrustServiceInfo> discoverByCnpj(String cnpj) throws IOException {
		return discoverByCnpj(cnpj, false);
	}

	public List<TrustServiceInfo> discoverByCnpj(String cnpj, boolean throwExceptions) throws IOException {

		if (Util.isNullOrEmpty(cnpj)) {
			throw new RuntimeException("The provided CNPJ is not valid");
		}

		List<String> args = new ArrayList<String>();

		// Add CNPJ
		args.add("--cnpj");
		args.add(cnpj);

		if (throwExceptions) {
			args.add("--throw");
		}

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandDiscoverServices, args);

		// Parse output and return result.
		DiscoverServicesResultModel model = parseOutput(response.getOutput()[0], DiscoverServicesResultModel.class);
		List<TrustServiceInfo> services = new ArrayList<TrustServiceInfo>();
		for (TrustServiceInfoModel serviceModel : model.getServices()) {
			services.add(new TrustServiceInfo(serviceModel));
		}
		return services;
	}

	public List<TrustServiceAuthParameters> discoverByCpfAndStartAuth(String cpf, String redirectUrl) throws IOException {
		return discoverByCpfAndStartAuth(cpf, redirectUrl, TrustServiceSessionTypes.SIGNATURE_SESSION);
	}

	public List<TrustServiceAuthParameters> discoverByCpfAndStartAuth(String cpf, String redirectUrl, TrustServiceSessionTypes sessionType) throws IOException {
		return discoverByCpfAndStartAuth(cpf, redirectUrl, sessionType, null);
	}

	public List<TrustServiceAuthParameters> discoverByCpfAndStartAuth(String cpf, String redirectUrl, TrustServiceSessionTypes sessionType, String customState) throws IOException {
		return discoverByCpfAndStartAuth(cpf, redirectUrl, sessionType, customState, false, 0);
	}

	public List<TrustServiceAuthParameters> discoverByCpfAndStartAuth(String cpf, String redirectUrl, TrustServiceSessionTypes sessionType, String customState, boolean throwExceptions, Integer lifetime) throws IOException {
		if (Util.isNullOrEmpty(cpf)) {
			throw new RuntimeException("The provided CPF is not valid");
		}

		if (Util.isNullOrEmpty(redirectUrl)) {
			throw new RuntimeException("The provided redirectUrl is not valid");
		}

		if (sessionType == null) {
			throw new RuntimeException("No sessionType was provided");
		}

		List<String> args = new ArrayList<String>();

		// Add CPF
		args.add("--cpf");
		args.add(cpf);

		// Add redirectUrl
		args.add("--redirect-url");
		args.add(redirectUrl);

		// Add sessionType
		if (sessionType != null) {
			args.add("--session-type");
			args.add(sessionType.getValue());
		}

		if (customState != null) {
			args.add("--custom-state");
			args.add(customState);
		}

		if (throwExceptions) {
			args.add("--throw");
		}

		if(lifetime != 0) {
			String lifetimeString = Integer.toString(lifetime);
			args.add("--session-lifetime");
			args.add(lifetimeString);
		}

		// This operation can only be used on versions greater than 1.17 of the PKI
		// Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandDiscoverServices, args);

		// Parse output and return result.
		DiscoverServicesResultModel model = parseOutput(response.getOutput()[0], DiscoverServicesResultModel.class);
		List<TrustServiceAuthParameters> authParameters = new ArrayList<TrustServiceAuthParameters>();
		for (TrustServiceAuthParametersModel authParametersModel : model.getAuthParameters()) {
			authParameters.add(new TrustServiceAuthParameters(authParametersModel));
		}
		return authParameters;
	}

	public List<TrustServiceAuthParameters> discoverByCnpjAndStartAuth(String cnpj, String redirectUrl) throws IOException {
		return discoverByCnpjAndStartAuth(cnpj, redirectUrl, TrustServiceSessionTypes.SIGNATURE_SESSION);
	}

	public List<TrustServiceAuthParameters> discoverByCnpjAndStartAuth(String cnpj, String redirectUrl, TrustServiceSessionTypes sessionType) throws IOException {
		return discoverByCnpjAndStartAuth(cnpj, redirectUrl, sessionType, null);
	}

	public List<TrustServiceAuthParameters> discoverByCnpjAndStartAuth(String cnpj, String redirectUrl, TrustServiceSessionTypes sessionType, String customState) throws IOException {
		return discoverByCnpjAndStartAuth(cnpj, redirectUrl, sessionType, customState, false, 0);
	}

	public List<TrustServiceAuthParameters> discoverByCnpjAndStartAuth(String cnpj, String redirectUrl, TrustServiceSessionTypes sessionType, String customState, boolean throwExceptions, Integer lifetime) throws IOException {
		if (Util.isNullOrEmpty(cnpj)) {
			throw new RuntimeException("The provided CNPJ is not valid");
		}

		if (Util.isNullOrEmpty(redirectUrl)) {
			throw new RuntimeException("The provided redirectUrl is not valid");
		}

		if (sessionType == null) {
			throw new RuntimeException("No sessionType was provided");
		}

		List<String> args = new ArrayList<String>();

		// Add CPF
		args.add("--cnpj");
		args.add(cnpj);

		// Add redirectUrl
		args.add("--redirect-url");
		args.add(redirectUrl);

		// Add sessionType
		args.add("--session-type");
		args.add(sessionType.getValue());

		if (customState != null) {
			args.add("--custom-state");
			args.add(customState);
		}

		if (throwExceptions) {
			args.add("--throw");
		}

		if(lifetime != 0) {
			String lifetimeString = Integer.toString(lifetime);
			args.add("--session-lifetime");
			args.add(lifetimeString);
		}

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandDiscoverServices, args);

		// Parse output and return result.
		DiscoverServicesResultModel model = parseOutput(response.getOutput()[0], DiscoverServicesResultModel.class);
		List<TrustServiceAuthParameters> authParameters = new ArrayList<TrustServiceAuthParameters>();
		for (TrustServiceAuthParametersModel authParametersModel : model.getAuthParameters()) {
			authParameters.add(new TrustServiceAuthParameters(authParametersModel));
		}
		return authParameters;
	}

	public TrustServiceSessionResult passwordAuthorize(String service, String username, String password, Integer lifetime) throws IOException {
		return passwordAuthorize(service, username, password, TrustServiceSessionTypes.SIGNATURE_SESSION, lifetime);
	}

	public TrustServiceSessionResult passwordAuthorize(String service, String username, String password, TrustServiceSessionTypes sessionType, Integer lifetime) throws IOException {
		if (Util.isNullOrEmpty(service)) {
			throw new RuntimeException("The provided service is not valid");
		}

		if (Util.isNullOrEmpty(username)) {
			throw new RuntimeException("The provided username is not valid");
		}

		if (Util.isNullOrEmpty(password)) {
			throw new RuntimeException("The provided password is not valid");
		}

		if (sessionType == null) {
			throw new RuntimeException("No sessionType was provided");
		}

		List<String> args = new ArrayList<String>();

		// Add service.
		args.add(service);

		// Add username.
		args.add(username);

		// Add password.
		args.add(password);

		// Add sessionType.
		args.add(sessionType.getValue());

		// Add session lifetime
		if(lifetime != 0) {
			String lifetimeString = Integer.toString(lifetime);
			args.add("--session-lifetime");
			args.add(lifetimeString);
		}

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandPasswordAuthorize, args);

		// Parse output and return result.
		TrustServiceSessionResultModel model = parseOutput(response.getOutput()[0], TrustServiceSessionResultModel.class);
		return new TrustServiceSessionResult(model);
	}

	public TrustServiceSessionResult completeAuth(String code, String state) throws IOException {
		if (Util.isNullOrEmpty(code)) {
			throw new RuntimeException("The provided code is not valid");
		}

		if (Util.isNullOrEmpty(state)) {
			throw new RuntimeException("The provided state is not valid");
		}

		List<String> args = new ArrayList<String>();

		// Add code.
		args.add(code);

		// Add state.
		args.add(state);

		// This operation can only be used on versions greater than 1.17 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.17"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandCompleteServiceAuth, args);

		// Parse output and return result.
		TrustServiceSessionResultModel model = parseOutput(response.getOutput()[0], TrustServiceSessionResultModel.class);
		return new TrustServiceSessionResult(model);
	}
}
