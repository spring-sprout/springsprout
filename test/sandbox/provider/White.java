package sandbox.provider;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Provider;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2010. 1. 12
 * Time: 오후 9:45:33
 */
public class White {

    @Autowired
    private Provider<Ship> shipProvider;

    public void hi(){
        System.out.println(shipProvider.get());
    }

}
