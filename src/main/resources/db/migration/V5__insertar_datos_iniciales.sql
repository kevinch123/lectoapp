INSERT INTO etapa (
    nombre,
    descripcion,
    objetivo_pedagogico,
    orden,
    activo
)
VALUES
    ('Etapa 1 - Exploración espacial', 'Descubre posiciones y direcciones básicas.', 'Desarrollar orientación espacial y reconocimiento de ubicación.', 1, TRUE),
    ('Etapa 2 - Reconocimiento de letras', 'Identifica vocales y consonantes con claridad.', 'Fortalecer el reconocimiento de letras básicas y su sonido.', 2, TRUE),
    ('Etapa 3 - Formación de sílabas', 'Forma sílabas simples y reconoce su estructura.', 'Desarrollar la unión de consonantes y vocales para formar sílabas.', 3, TRUE),
    ('Etapa 4 - Completar palabras', 'Completa palabras con la opción correcta.', 'Mejorar la comprensión de palabras básicas y su escritura.', 4, TRUE),
    ('Etapa 5 - Sílabas complejas', 'Reconoce sílabas más complejas.', 'Ampliar la capacidad para reconocer estructuras silábicas avanzadas.', 5, TRUE),
    ('Etapa 6 - Completar frases', 'Completa oraciones con palabras adecuadas.', 'Desarrollar comprensión semántica y coherencia en frases.', 6, TRUE),
    ('Etapa 7 - Comprensión lectora', 'Lee textos cortos y responde preguntas.', 'Mejorar la comprensión de textos breves y la interpretación.', 7, TRUE),
    ('Etapa 8 - Comprensión integral', 'Integra habilidades de lectura y análisis.', 'Fortalecer la comprensión profunda de textos simples.', 8, TRUE),
    ('Etapa 9 - Lectura final', 'Aplica todo lo aprendido en lectura.', 'Desarrollar una comprensión lectora completa y autónoma.', 9, TRUE);

INSERT INTO actividad (
    nombre,
    descripcion,
    tipo_actividad,
    dificultad,
    configuracion,
    activo,
    etapa_id
)
VALUES
(
    'Ubica el objeto',
    'Identifica la ubicación espacial indicada.',
    'SELECCION',
    'FACIL',
    '{
        "items":[
            {
                "pregunta":"¿Dónde está el gato?",
                "opciones":["Arriba","Abajo","Izquierda","Derecha"],
                "respuestaCorrecta":"Arriba",
                "recurso":"gato.png"
            },
            {
                "pregunta":"¿Dónde está el avión?",
                "opciones":["Arriba","Abajo","Izquierda","Derecha"],
                "respuestaCorrecta":"Arriba",
                "recurso":"avion.png"
            },
            {
                "pregunta":"¿Dónde está la pelota?",
                "opciones":["Arriba","Abajo","Izquierda","Derecha"],
                "respuestaCorrecta":"Abajo",
                "recurso":"pelota.png"
            }
        ]
    }'::jsonb,
    TRUE,
    1
),

(
    'Reconoce las vocales',
    'Selecciona la vocal correcta.',
    'SELECCION',
    'FACIL',
    '{
        "items":[
            {
                "pregunta":"Selecciona la vocal A",
                "opciones":["A","M","P","S"],
                "respuestaCorrecta":"A",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la vocal E",
                "opciones":["T","L","E","R"],
                "respuestaCorrecta":"E",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la vocal I",
                "opciones":["B","I","F","N"],
                "respuestaCorrecta":"I",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la vocal O",
                "opciones":["O","P","M","T"],
                "respuestaCorrecta":"O",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la vocal U",
                "opciones":["L","U","R","S"],
                "respuestaCorrecta":"U",
                "recurso":""
            }
        ]
    }'::jsonb,
    TRUE,
    2
),

(
    'Reconoce las consonantes',
    'Selecciona la consonante indicada.',
    'SELECCION',
    'FACIL',
    '{
        "items":[
            {
                "pregunta":"Selecciona la consonante M",
                "opciones":["M","A","E","I"],
                "respuestaCorrecta":"M",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la consonante P",
                "opciones":["A","P","O","U"],
                "respuestaCorrecta":"P",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la consonante S",
                "opciones":["E","I","S","A"],
                "respuestaCorrecta":"S",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la consonante L",
                "opciones":["L","O","U","A"],
                "respuestaCorrecta":"L",
                "recurso":""
            },
            {
                "pregunta":"Selecciona la consonante T",
                "opciones":["I","E","T","O"],
                "respuestaCorrecta":"T",
                "recurso":""
            }
        ]
    }'::jsonb,
    TRUE,
    3
),
(
    'Forma sílabas directas',
    'Forma correctamente sílabas uniendo consonantes y vocales.',
    'ARRASTRAR',
    'FACIL',
    '{
        "items":[
            {
                "letras":["M","A"],
                "respuestaCorrecta":"MA"
            },
            {
                "letras":["P","A"],
                "respuestaCorrecta":"PA"
            },
            {
                "letras":["S","O"],
                "respuestaCorrecta":"SO"
            },
            {
                "letras":["L","U"],
                "respuestaCorrecta":"LU"
            },
            {
                "letras":["T","E"],
                "respuestaCorrecta":"TE"
            }
        ]
    }'::jsonb,
    TRUE,
    4
),

(
    'Completa la palabra',
    'Selecciona la opción correcta para completar la palabra.',
    'SELECCION',
    'MEDIA',
    '{
        "items":[
            {
                "pregunta":"Completa la palabra C _ S A",
                "opciones":["A","E","I","O"],
                "respuestaCorrecta":"A",
                "recurso":"casa.png"
            },
            {
                "pregunta":"Completa la palabra P _ T O",
                "opciones":["A","E","I","U"],
                "respuestaCorrecta":"A",
                "recurso":"pato.png"
            },
            {
                "pregunta":"Completa la palabra M _ S A",
                "opciones":["E","I","O","U"],
                "respuestaCorrecta":"E",
                "recurso":"mesa.png"
            },
            {
                "pregunta":"Completa la palabra S _ L",
                "opciones":["A","E","O","U"],
                "respuestaCorrecta":"O",
                "recurso":"sol.png"
            },
            {
                "pregunta":"Completa la palabra L _ N A",
                "opciones":["U","O","I","E"],
                "respuestaCorrecta":"U",
                "recurso":"luna.png"
            }
        ]
    }'::jsonb,
    TRUE,
    5
),

(
    'Reconoce sílabas complejas',
    'Selecciona la sílaba correcta para completar la palabra.',
    'SELECCION',
    'MEDIA',
    '{
        "items":[
            {
                "pregunta":"Completa la palabra _ ato",
                "opciones":["Pla","Pra","Bra","Gra"],
                "respuestaCorrecta":"Pla",
                "recurso":"plato.png"
            },
            {
                "pregunta":"Completa la palabra _ usa",
                "opciones":["Bru","Blu","Gru","Dru"],
                "respuestaCorrecta":"Bru",
                "recurso":"bruja.png"
            },
            {
                "pregunta":"Completa la palabra _ en",
                "opciones":["Tre","Pre","Cre","Gre"],
                "respuestaCorrecta":"Tre",
                "recurso":"tren.png"
            },
            {
                "pregunta":"Completa la palabra _ esa",
                "opciones":["Fre","Cre","Bre","Dre"],
                "respuestaCorrecta":"Fre",
                "recurso":"fresa.png"
            },
            {
                "pregunta":"Completa la palabra _ anco",
                "opciones":["Bla","Cla","Pla","Gra"],
                "respuestaCorrecta":"Bla",
                "recurso":"blanco.png"
            }
        ]
    }'::jsonb,
    TRUE,
    6
),
(
    'Completa la frase',
    'Selecciona la palabra correcta para completar la oración.',
    'SELECCION',
    'MEDIA',
    '{
        "items":[
            {
                "pregunta":"El perro ____ en el parque.",
                "opciones":["corre","mesa","amarillo","ventana"],
                "respuestaCorrecta":"corre",
                "recurso":"perro_parque.png"
            },
            {
                "pregunta":"La niña come una ____.",
                "opciones":["manzana","silla","pelota","camisa"],
                "respuestaCorrecta":"manzana",
                "recurso":"manzana.png"
            },
            {
                "pregunta":"El pez nada en el ____.",
                "opciones":["agua","árbol","cielo","carro"],
                "respuestaCorrecta":"agua",
                "recurso":"pez.png"
            },
            {
                "pregunta":"El pájaro vuela en el ____.",
                "opciones":["cielo","mar","suelo","río"],
                "respuestaCorrecta":"cielo",
                "recurso":"pajaro.png"
            },
            {
                "pregunta":"La vaca da ____.",
                "opciones":["leche","pan","jugo","arroz"],
                "respuestaCorrecta":"leche",
                "recurso":"vaca.png"
            }
        ]
    }'::jsonb,
    TRUE,
    7
),

(
    'Comprensión lectora',
    'Lee un texto corto y responde la pregunta.',
    'SELECCION',
    'DIFICIL',
    '{
        "items":[
            {
                "texto":"Ana tiene un gato. Todos los días juega con él en el jardín.",
                "pregunta":"¿Con quién juega Ana?",
                "opciones":["Con un perro","Con un gato","Con un conejo","Con un pájaro"],
                "respuestaCorrecta":"Con un gato",
                "recurso":""
            },
            {
                "texto":"Luis fue al colegio en bicicleta.",
                "pregunta":"¿En qué fue Luis al colegio?",
                "opciones":["Bus","Bicicleta","Carro","Moto"],
                "respuestaCorrecta":"Bicicleta",
                "recurso":""
            },
            {
                "texto":"María compró pan en la tienda.",
                "pregunta":"¿Qué compró María?",
                "opciones":["Leche","Pan","Queso","Jugo"],
                "respuestaCorrecta":"Pan",
                "recurso":""
            },
            {
                "texto":"Pedro juega fútbol con sus amigos.",
                "pregunta":"¿Qué deporte juega Pedro?",
                "opciones":["Baloncesto","Fútbol","Tenis","Natación"],
                "respuestaCorrecta":"Fútbol",
                "recurso":""
            },
            {
                "texto":"El árbol tiene muchas hojas verdes.",
                "pregunta":"¿De qué color son las hojas?",
                "opciones":["Azules","Verdes","Rojas","Amarillas"],
                "respuestaCorrecta":"Verdes",
                "recurso":""
            }
        ]
    }'::jsonb,
    TRUE,
    8
),

(
    'Comprensión lectora integral',
    'Integra las habilidades adquiridas durante el proceso de aprendizaje.',
    'SELECCION',
    'DIFICIL',
    '{
        "items":[
            {
                "texto":"María fue al parque con su hermano. Jugaron en los columpios y luego regresaron felices a casa.",
                "pregunta":"¿A dónde regresaron María y su hermano?",
                "opciones":["Al colegio","Al parque","A casa","A la tienda"],
                "respuestaCorrecta":"A casa",
                "recurso":""
            },
            {
                "texto":"Juan leyó un libro antes de dormir.",
                "pregunta":"¿Qué hizo Juan antes de dormir?",
                "opciones":["Jugó","Leyó un libro","Corrió","Comió"],
                "respuestaCorrecta":"Leyó un libro",
                "recurso":""
            },
            {
                "texto":"Laura regó las flores porque hacía mucho calor.",
                "pregunta":"¿Por qué Laura regó las flores?",
                "opciones":["Porque llovía","Porque hacía calor","Porque era de noche","Porque estaban secas de pintura"],
                "respuestaCorrecta":"Porque hacía calor",
                "recurso":""
            },
            {
                "texto":"Carlos desayunó cereal con leche antes de ir a clases.",
                "pregunta":"¿Qué desayunó Carlos?",
                "opciones":["Pan","Cereal con leche","Fruta","Sopa"],
                "respuestaCorrecta":"Cereal con leche",
                "recurso":""
            },
            {
                "texto":"Sofía encontró un cachorro perdido y llamó a su mamá para ayudarlo.",
                "pregunta":"¿Qué encontró Sofía?",
                "opciones":["Un gato","Un cachorro","Un pájaro","Un conejo"],
                "respuestaCorrecta":"Un cachorro",
                "recurso":""
            }
        ]
    }'::jsonb,
    TRUE,
    9
);