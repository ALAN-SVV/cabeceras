package service;

import models.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {  // 2 usages = Elvis
    @Override  // 1 usage = Elvis
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "laptop", "computation", 523.21),
                new Producto(2L, "Mouse", "inalambrico", 15.25),
                new Producto(3L, "Impresora", "tinta continua", 256.25)
        );
    }
}

