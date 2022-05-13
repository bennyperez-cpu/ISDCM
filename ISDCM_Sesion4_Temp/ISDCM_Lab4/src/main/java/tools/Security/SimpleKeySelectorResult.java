/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.Security;

import java.security.Key;
import java.security.PublicKey;
import javax.xml.crypto.KeySelectorResult;

/**
 *
 * @author Benny Hammer Pérez Vasquez
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
