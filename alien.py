import pygame


class Alien:

    def __init__(self, ai_game):

        self.screen = ai_game.screen
        self.screen_rect = ai_game.screen.get_rect()

        self.image = pygame.image.load('images/alien.bmp')
        self.rect = self.image.get_rect()
        self.rect.x = 0
        self.rect.y = 0

    def blitme(self):
        self.screen.blit(self.image, self.rect)

