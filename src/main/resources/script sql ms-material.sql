INSERT INTO MS_MATERIAL.UNIDAD (ID_UNI,DESC_UNI ) VALUES
    (1, 'UNIDADES'),
    (2, 'METROS'),
    (3, 'LITROS'),
    (4, 'KILOS');

INSERT INTO MS_MATERIAL.MATERIAL (NOM_MAT, DESC_MAT, PREC_MAT, STOCK_ACT_MAT, STOCK_MIN_MAT, ID_UNI) VALUES
    ('Ladrillo', 'Descripcion material 1', 11.0, 111, 11, 1),
    ('Cemento', 'Descripcion material 2', 22.0, 222, 22, 4),
    ('Yeso', 'Descripcion material 3', 30.0, 300, 13, 4);