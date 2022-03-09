<?php

// This file has been auto-generated by the Symfony Routing Component.

return [
    '_preview_error' => [['code', '_format'], ['_controller' => 'error_controller::preview', '_format' => 'html'], ['code' => '\\d+'], [['variable', '.', '[^/]++', '_format'], ['variable', '/', '\\d+', 'code'], ['text', '/_error']], [], []],
    'avis_index' => [[], ['_controller' => 'App\\Controller\\AvisController::index'], [], [['text', '/avis/']], [], []],
    'avis_new' => [['id'], ['_controller' => 'App\\Controller\\AvisController::new'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/avis/new']], [], []],
    'avis_show' => [['id'], ['_controller' => 'App\\Controller\\AvisController::show'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/avis']], [], []],
    'avis_edit' => [['id'], ['_controller' => 'App\\Controller\\AvisController::edit'], [], [['text', '/edit'], ['variable', '/', '[^/]++', 'id'], ['text', '/avis']], [], []],
    'avis_delete' => [['id'], ['_controller' => 'App\\Controller\\AvisController::delete'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/avis']], [], []],
    'fournisseur_index' => [[], ['_controller' => 'App\\Controller\\FournisseurController::index'], [], [['text', '/fournisseur/']], [], []],
    'fournisseur_new' => [[], ['_controller' => 'App\\Controller\\FournisseurController::new'], [], [['text', '/fournisseur/new']], [], []],
    'fournisseur_show' => [['id'], ['_controller' => 'App\\Controller\\FournisseurController::show'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/fournisseur']], [], []],
    'fournisseur_edit' => [['id'], ['_controller' => 'App\\Controller\\FournisseurController::edit'], [], [['text', '/edit'], ['variable', '/', '[^/]++', 'id'], ['text', '/fournisseur']], [], []],
    'fournisseur_delete' => [['id'], ['_controller' => 'App\\Controller\\FournisseurController::delete'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/fournisseur']], [], []],
    'listo' => [[], ['_controller' => 'App\\Controller\\ProduitController::listo'], [], [['text', '/produit/listo']], [], []],
    'produit_index' => [[], ['_controller' => 'App\\Controller\\ProduitController::index'], [], [['text', '/produit/']], [], []],
    'produit_front' => [[], ['_controller' => 'App\\Controller\\ProduitController::front'], [], [['text', '/produit/front']], [], []],
    'produit_new' => [[], ['_controller' => 'App\\Controller\\ProduitController::new'], [], [['text', '/produit/new']], [], []],
    'produit_show' => [['id'], ['_controller' => 'App\\Controller\\ProduitController::show'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/produit']], [], []],
    'produit_edit' => [['id'], ['_controller' => 'App\\Controller\\ProduitController::edit'], [], [['text', '/edit'], ['variable', '/', '[^/]++', 'id'], ['text', '/produit']], [], []],
    'produit_delete' => [['id'], ['_controller' => 'App\\Controller\\ProduitController::delete'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/produit']], [], []],
];
