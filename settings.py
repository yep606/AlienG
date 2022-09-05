class Settings:
    """Options for game """

    def __init__(self):
        # Main settings
        self.screen_width = 600
        self.screen_height = 600
        self.bg_color = (230, 230, 230)

        # Ship settings
        self.ship_speed = 0.7

        # Bullet settings
        self.bullet_speed = 1
        self.bullet_width = 3
        self.bullet_height = 15
        self.bullet_color = (60, 60, 60)
        self.bullets_allowed = 3

