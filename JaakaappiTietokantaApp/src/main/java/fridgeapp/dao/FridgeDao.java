/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fridgeapp.dao;

import fridgeapp.domain.Fridge;
import fridgeapp.domain.FridgeItem;

/**
 *
 * @author Tero
 */
public interface FridgeDao {
    
    Fridge create(Fridge fridge) throws Exception;
}
