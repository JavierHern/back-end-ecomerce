-- Seed inicial basado en los mocks del front
-- Tabla genérica: products(sku, name, description, stock, price, category)
-- Ignora filas con claves duplicadas (MySQL)
INSERT IGNORE INTO products (
        sku,
        name,
        category,
        price,
        image,
        description,
        featured,
        news,
        stock
    )
VALUES (
        'SKU-1',
        'CubeX 3x3',
        'Cubo de velocidad, Speedcube',
        29.99,
        '/images/cubo3x3.png',
        'El clásico reinventado. Este cubo 3x3 está diseñado para la máxima velocidad y rendimiento, con un giro suave y un corte de esquinas excepcional. Perfecto tanto para principiantes como para speedcubers avanzados.',
        true,
        false,
        150
    ),
    (
        'SKU-2',
        'PyraX Speed',
        'Pyraminx, Pirámide, Speedcube',
        24.99,
        '/images/pyra.png',
        'Atrévete con un desafío diferente. El PyraX ofrece una experiencia de resolución única con su forma tetraédrica. Su mecanismo de alta calidad asegura giros rápidos y precisos en cada movimiento.',
        true,
        false,
        120
    ),
    (
        'SKU-3',
        'MegaX Ultimate',
        'Megaminx, Speedcube',
        39.99,
        '/images/mega.png',
        'Lleva tus habilidades al siguiente nivel con el MegaX Ultimate. Con 12 caras y 50 piezas móviles, este dodecaedro es el desafío definitivo para los solucionadores de puzzles más dedicados.',
        true,
        false,
        80
    ),
    (
        'SKU-4',
        'CubeX 4x4',
        'Cubo de velocidad, Speedcube',
        34.99,
        '/images/cubo4x4.png',
        'Más grande, más complejo. El cubo 4x4, también conocido como ''La Venganza'', añade una nueva capa de dificultad sin centros fijos. Ideal para quienes ya dominan el 3x3 y buscan un nuevo reto.',
        true,
        false,
        100
    ),
    (
        'SKU-5',
        'CubeX Pro',
        'Cubo Edición limitada, Speedcube',
        49.99,
        '/images/cuboair3x3.png',
        'La joya de la corona. El CubeX Pro es una edición especial ultraligera con un mecanismo magnético avanzado. Ofrece una sensación de giro inigualable y un control superior para los cubers más exigentes.',
        true,
        false,
        30
    ),
    (
        'SKU-6',
        'CubeX Mini 2x2',
        'Cubo de bolsillo, Speedcube',
        19.99,
        '/images/cubo2x2.png',
        'La introducción perfecta al mundo del cubing. El 2x2 es rápido de resolver, divertido y fácil de llevar a todas partes. Ideal para niños y principiantes que quieren aprender los fundamentos.',
        true,
        false,
        200
    ),
    (
        'SKU-7',
        'SkewbX Pro',
        'Skewb, Speedcube',
        27.99,
        '/images/skewb.png',
        'Un puzzle que te hará pensar de otra manera. El Skewb gira en torno a sus esquinas, ofreciendo una lógica de resolución completamente distinta a los cubos tradicionales. Un reto refrescante y muy entretenido.',
        true,
        false,
        90
    ),
    (
        'SKU-8',
        'CubeX 5x5 Master',
        'Cubo Avanzado, Speedcube',
        44.99,
        '/images/cubo5x5.png',
        'Para los verdaderos maestros del cubo. El 5x5 aumenta la complejidad con más capas y piezas que alinear. Requiere paciencia, estrategia y un dominio de algoritmos avanzados.',
        true,
        false,
        70
    );