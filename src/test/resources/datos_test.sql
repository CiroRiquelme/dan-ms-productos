INSERT INTO MS_MATERIAL.UNIDAD (ID_UNI,DESC_UNI ) VALUES
    (1, 'UNIDADES'),
    (2, 'METROS'),
    (3, 'LITROS'),
    (4, 'KILOS');

INSERT INTO MS_MATERIAL.MATERIAL (NOM_MAT, DESC_MAT, PREC_MAT, STOCK_ACT_MAT, STOCK_MIN_MAT, ID_UNI) VALUES
    ('Ladrillo', 'Descripcion ladrillos', 10.0, 100, 10, 1),
    ('Cemento', 'Descripcion cemento', 20.0, 200, 10, 4),
    ('Yeso', 'Descripcion yeso', 30.0, 300, 10, 4);