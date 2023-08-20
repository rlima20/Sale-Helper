package com.example.mystore

import com.example.mystore.model.Product
import com.example.mystore.model.Transaction
import java.util.Date

val listOfProductsLocal = listOf(
    Product(
        id = 0,
        title = "Samsung A30S",
        description = "O Samsung Galaxy A30s é um smartphone Android completo, que não tem muito a invejar aos mais avançados dispositivos. Surpreendente é sua tela Touchscreen de 6.4 polegadas, que coloca esse Samsung no topo de sua categoria. A resolução também é alta: 1560x720 pixel.",
        quantity = 5,
        maxQuantityToBuy = 9,
        purchasePrice = 1400.0,
        salePrice = 2000.0,
        imageUrl = "https://s2-techtudo.glbimg.com/a-iQhuilJ0254f59X0-Rv8hKRXs=/184x0:1751x1080/1000x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2020/E/7/oUKdRERJqXVqfkGus4dQ/2.png",
    ),
    Product(
        id = 1,
        title = "Fone de Ouvido Som HD e Microfone Motorola Moto XT 120",
        description = "Qualidade de som HD: O Moto XT 120 possui drivers de 40mm de alta definição que oferecem som claro e graves potentes. Ouça suas músicas e faça chamadas com qualidade de som superior.",
        quantity = 3,
        maxQuantityToBuy = 9,
        purchasePrice = 90.0,
        salePrice = 130.0,
        imageUrl = "https://m.media-amazon.com/images/I/51vn4Ywvx+L._AC_SL1080_.jpg",
    ),
    Product(
        id = 2,
        title = "Echo Dot (3ª Geração): Smart Speaker com Alexa - Cor Preta",
        description = "O Echo Dot é o nosso smart speaker de maior sucesso. Controlado por voz com Alexa, ele é perfeito para qualquer ambiente. Você pode pedir músicas, notícias, informações e muito mais. Além de ligar para amigos e familiares e controlar dispositivos compatíveis de casa inteligente com sua voz.",
        quantity = 10,
        maxQuantityToBuy = 9,
        purchasePrice = 200.0,
        salePrice = 350.0,
        imageUrl = "https://m.media-amazon.com/images/I/51+iQTwDsXL._AC_SL1000_.jpg",
    ),
    Product(
        id = 3,
        title = "Echo Show 5 (2ª Geração): Smart Display de 5\" com Alexa e câmera de 2 MP - Cor Preta",
        description = "Aproveite seu dia com Alexa: defina alarmes e timers, cheque seu calendário ou as notícias, faça chamadas de vídeo com a câmera de 2 MP para amigos e familiares que tenham o aplicativo Alexa ou um dispositivo Echo com tela e reproduza músicas ou séries. Tudo com sua voz.",
        quantity = 5,
        maxQuantityToBuy = 9,
        purchasePrice = 250.0,
        salePrice = 400.0,
        imageUrl = "https://m.media-amazon.com/images/I/71fzcZQlbqS._AC_SL1500_.jpg",
    ),
    Product(
        id = 1,
        title = "Coca-Cola",
        description = "Refrigerante de cola",
        quantity = 0,
        maxQuantityToBuy = 9,
        purchasePrice = 5.0,
        salePrice = 10.0,
        imageUrl = "https://veja.abril.com.br/wp-content/uploads/2016/06/logo-coca-cola-caminhao-original4.jpeg?quality=90&strip=info&w=1040&h=585&crop=1",
    ),
)

val listOfTransactions = listOf(
    Transaction(
        product = listOfProductsLocal[4],
        transactionType = TransactionType.SALE,
        transactionDate = Date(),
        unitValue = 10.0,
        quantity = 2,
        transactionAmount = 20.0,
    ),
    Transaction(
        product = listOfProductsLocal[0],
        transactionType = TransactionType.SALE,
        transactionDate = Date(),
        unitValue = 2000.0,
        quantity = 2,
        transactionAmount = 4000.0,
    ),
    Transaction(
        product = listOfProductsLocal[2],
        transactionType = TransactionType.SALE,
        transactionDate = Date(),
        unitValue = 350.0,
        quantity = 1,
        transactionAmount = 350.0,
    ),
    Transaction(
        product = listOfProductsLocal[4],
        transactionType = TransactionType.PURCHASE,
        transactionDate = Date(),
        unitValue = 5.0,
        quantity = 10,
        transactionAmount = 50.0,
    ),
    Transaction(
        product = listOfProductsLocal[3],
        transactionType = TransactionType.PURCHASE,
        transactionDate = Date(),
        unitValue = 250.0,
        quantity = 4,
        transactionAmount = 1000.0,
    ),
)
