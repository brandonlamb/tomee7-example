//'use strict';

var game = new Phaser.Game(1024, 400, Phaser.AUTO, 'World', {
    preload: preload,
    create: create,
    update: update,
    render: render
});

var player;
var cursors;
var PLAYER_SPEED = 100;
var wasd;

function preload() {
    game.load.baseURL = 'http://localhost:8080/phaser02/';
    game.load.crossOrigin = 'anonymous';

    game.load.image('background', 'assets/debug-grid-1920x1920.png');
    game.load.image('ship-alien1', 'assets/alien1.png');
    game.load.image('ship-alien2', 'assets/alien2.png');
    game.load.image('ship-alien3', 'assets/alien3.png');
}

function create() {
    var playerShip = 'ship-alien1';

    game.scale.fullScreenScaleMode = Phaser.ScaleManager.SHOW_ALL;
    game.add.tileSprite(0, 0, 1920, 1920, 'background');
    game.world.setBounds(0, 0, 3000, 3000);
    game.physics.startSystem(Phaser.Physics.P2JS);
    cursors = game.input.keyboard.createCursorKeys();

    player = game.add.sprite(game.world.centerX, game.world.centerY, playerShip);
    player.scale.setTo(0.3, 0.3);
    game.physics.p2.enable(player);
    game.camera.follow(player);

    wasd = {
        UP: game.input.keyboard.addKey(Phaser.KeyCode.W),
        DOWN: game.input.keyboard.addKey(Phaser.KeyCode.S),
        LEFT: game.input.keyboard.addKey(Phaser.KeyCode.A),
        RIGHT: game.input.keyboard.addKey(Phaser.KeyCode.D)
    };
}

function update() {
    player.body.setZeroVelocity();
    if (cursors.up.isDown || wasd.UP.isDown) {
        player.body.moveUp(PLAYER_SPEED)
    }

    if (cursors.down.isDown || wasd.DOWN.isDown) {
        player.body.moveDown(PLAYER_SPEED);
    }

    if (cursors.left.isDown || wasd.LEFT.isDown) {
        player.body.moveLeft(PLAYER_SPEED);
    }

    if (cursors.right.isDown || wasd.RIGHT.isDown) {
        player.body.moveRight(PLAYER_SPEED);
    }
}

function render() {
    game.debug.cameraInfo(game.camera, 32, 32);
    game.debug.spriteCoords(player, 32, 500);
}
