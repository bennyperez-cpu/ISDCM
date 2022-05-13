/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helpers.Security;

import java.security.Key;
import java.security.PublicKey;
import javax.xml.crypto.KeySelectorResult;

/**
 *
 * @author nfran
 */
public class SimpleKeySelectorResult implements KeySelectorResult {
	private PublicKey pk;

	SimpleKeySelectorResult(PublicKey pk) {
		this.pk = pk;
	}

	@Override
	public Key getKey() {
		return this.pk;
	}
}
