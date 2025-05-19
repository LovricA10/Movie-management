CREATE DATABASE MovieDB
GO

USE MovieDB
GO

-- User table
CREATE TABLE [User] (
    IDUser INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(100),
    Email NVARCHAR(100),
    [Password] NVARCHAR(100),
    Role NVARCHAR(50)
)
GO

-- Movie table
CREATE TABLE Movie (
    IDMovie INT PRIMARY KEY IDENTITY,
    Title NVARCHAR(200),
    Duration INT,
    StartDate NVARCHAR(90),
    PicturePath NVARCHAR(200)
)
GO

ALTER TABLE Movie
DROP COLUMN Duration;

ALTER TABLE Movie
ADD Description NVARCHAR(MAX);

ALTER TABLE Movie
ADD Link NVARCHAR(500);

-- Genre table
CREATE TABLE Genre (
    IDGenre INT PRIMARY KEY IDENTITY,
    GenreName NVARCHAR(100)
)
GO

-- MovieGenre table (many-to-many)
CREATE TABLE MovieGenre (
    IDMovieGenre INT PRIMARY KEY IDENTITY,
    MovieID INT,
    GenreID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie),
    FOREIGN KEY (GenreID) REFERENCES Genre(IDGenre)
)
GO

-- Actor table
CREATE TABLE Actor (
    IDActor INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100),
    LastName NVARCHAR(100),
    DateBirth NVARCHAR(90),
    PicturePath NVARCHAR(200)
)
GO

-- MovieActor table (many-to-many)
CREATE TABLE MovieActor (
    IDMovieActor INT PRIMARY KEY IDENTITY,
    MovieID INT,
    ActorID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie),
    FOREIGN KEY (ActorID) REFERENCES Actor(IDActor)
)
GO

-- Director table
CREATE TABLE Director (
    IDDirector INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100),
    LastName NVARCHAR(100),
    DateBirth NVARCHAR(90),
    PicturePath NVARCHAR(200)
)
GO

-- MovieDirector table (many-to-many)
CREATE TABLE MovieDirector (
    IDMovieDirector INT PRIMARY KEY IDENTITY,
    MovieID INT,
    DirectorID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie),
    FOREIGN KEY (DirectorID) REFERENCES Director(IDDirector)
)
GO

-- Movie_User table (user ratings, favorites, comments)
CREATE TABLE Movie_User (
    IDMovieUser INT PRIMARY KEY IDENTITY,
    MovieID INT,
    UserID INT,
    Rating INT,
    Comment NVARCHAR(900),
    Favorite BIT,
    FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie),
    FOREIGN KEY (UserID) REFERENCES [User](IDUser)
)
GO

-- === STORED PROCEDURES ===

-- USER
CREATE PROCEDURE createUser
    @Username NVARCHAR(100),
    @Email NVARCHAR(100),
    @Password NVARCHAR(100),
    @Role NVARCHAR(50),
    @IDUser INT OUTPUT
AS
BEGIN
    INSERT INTO [User] VALUES (@Username, @Email, @Password, @Role)
    SET @IDUser = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateUser
    @Username NVARCHAR(100),
    @Email NVARCHAR(100),
    @Password NVARCHAR(100),
    @Role NVARCHAR(50),
    @IDUser INT
AS
BEGIN
    UPDATE [User] SET
        Username = @Username,
        Email = @Email,
        [Password] = @Password,
        Role = @Role
    WHERE IDUser = @IDUser
END
GO

CREATE PROCEDURE deleteUser
    @IDUser INT
AS
BEGIN
    DELETE FROM [User] WHERE IDUser = @IDUser
END
GO

CREATE PROCEDURE selectUser
    @IDUser INT
AS
BEGIN
    SELECT * FROM [User] WHERE IDUser = @IDUser
END
GO

CREATE PROCEDURE selectUsers
AS
BEGIN
    SELECT * FROM [User]
END
GO

-- MOVIE
CREATE OR ALTER PROCEDURE createMovie
    @Title NVARCHAR(200),
    @Link NVARCHAR(500),
    @Description NVARCHAR(MAX),
    @StartDate NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDMovie INT OUTPUT
AS
BEGIN
    INSERT INTO Movie VALUES (@Title, @Link,@Description, @StartDate, @PicturePath)
    SET @IDMovie = SCOPE_IDENTITY()
END
GO

CREATE OR ALTER  PROCEDURE updateMovie
    @Title NVARCHAR(200),
    @Link NVARCHAR(500),
    @Description NVARCHAR(MAX),
    @StartDate NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDMovie INT
AS
BEGIN
    UPDATE Movie SET
        Title = @Title,
		Link = @Link,
        Description = @Description,
        StartDate = @StartDate,
        PicturePath = @PicturePath
    WHERE IDMovie = @IDMovie
END
GO

CREATE PROCEDURE deleteMovie
    @IDMovie INT
AS
BEGIN
    DELETE FROM Movie WHERE IDMovie = @IDMovie
END
GO

CREATE PROCEDURE selectMovie
    @IDMovie INT
AS
BEGIN
    SELECT * FROM Movie WHERE IDMovie = @IDMovie
END
GO

CREATE PROCEDURE selectMovies
AS
BEGIN
    SELECT * FROM Movie
END
GO

-- GENRE
CREATE PROCEDURE createGenre
    @GenreName NVARCHAR(100),
    @IDGenre INT OUTPUT
AS
BEGIN
    INSERT INTO Genre VALUES (@GenreName)
    SET @IDGenre = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateGenre
    @GenreName NVARCHAR(100),
    @IDGenre INT
AS
BEGIN
    UPDATE Genre SET GenreName = @GenreName WHERE IDGenre = @IDGenre
END
GO

CREATE PROCEDURE deleteGenre
    @IDGenre INT
AS
BEGIN
    DELETE FROM Genre WHERE IDGenre = @IDGenre
END
GO

CREATE PROCEDURE selectGenre
    @IDGenre INT
AS
BEGIN
    SELECT * FROM Genre WHERE IDGenre = @IDGenre
END
GO

CREATE PROCEDURE selectGenres
AS
BEGIN
    SELECT * FROM Genre
END
GO

-- ACTOR
CREATE PROCEDURE createActor
    @Name NVARCHAR(100),
    @LastName NVARCHAR(100),
    @DateBirth NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDActor INT OUTPUT
AS
BEGIN
    INSERT INTO Actor VALUES (@Name, @LastName, @DateBirth, @PicturePath)
    SET @IDActor = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateActor
    @Name NVARCHAR(100),
    @LastName NVARCHAR(100),
    @DateBirth NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDActor INT
AS
BEGIN
    UPDATE Actor SET
        Name = @Name,
        LastName = @LastName,
        DateBirth = @DateBirth,
        PicturePath = @PicturePath
    WHERE IDActor = @IDActor
END
GO

CREATE PROCEDURE deleteActor
    @IDActor INT
AS
BEGIN
    DELETE FROM Actor WHERE IDActor = @IDActor
END
GO

CREATE PROCEDURE selectActor
    @IDActor INT
AS
BEGIN
    SELECT * FROM Actor WHERE IDActor = @IDActor
END
GO

CREATE PROCEDURE selectActors
AS
BEGIN
    SELECT * FROM Actor
END
GO

-- DIRECTOR
CREATE PROCEDURE createDirector
    @Name NVARCHAR(100),
    @LastName NVARCHAR(100),
    @DateBirth NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDDirector INT OUTPUT
AS
BEGIN
    INSERT INTO Director VALUES (@Name, @LastName, @DateBirth, @PicturePath)
    SET @IDDirector = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateDirector
    @Name NVARCHAR(100),
    @LastName NVARCHAR(100),
    @DateBirth NVARCHAR(90),
    @PicturePath NVARCHAR(200),
    @IDDirector INT
AS
BEGIN
    UPDATE Director SET
        Name = @Name,
        LastName = @LastName,
        DateBirth = @DateBirth,
        PicturePath = @PicturePath
    WHERE IDDirector = @IDDirector
END
GO

CREATE PROCEDURE deleteDirector
    @IDDirector INT
AS
BEGIN
    DELETE FROM Director WHERE IDDirector = @IDDirector
END
GO

CREATE PROCEDURE selectDirector
    @IDDirector INT
AS
BEGIN
    SELECT * FROM Director WHERE IDDirector = @IDDirector
END
GO

CREATE PROCEDURE selectDirectors
AS
BEGIN
    SELECT * FROM Director
END
GO

-- MOVIE_USER
CREATE PROCEDURE createMovieUser
    @MovieID INT,
    @UserID INT,
    @Rating INT,
    @Comment NVARCHAR(900),
    @Favorite BIT,
    @IDMovieUser INT OUTPUT
AS
BEGIN
    INSERT INTO Movie_User VALUES (@MovieID, @UserID, @Rating, @Comment, @Favorite)
    SET @IDMovieUser = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovieUser
    @MovieID INT,
    @UserID INT,
    @Rating INT,
    @Comment NVARCHAR(900),
    @Favorite BIT,
    @IDMovieUser INT
AS
BEGIN
    UPDATE Movie_User SET
        MovieID = @MovieID,
        UserID = @UserID,
        Rating = @Rating,
        Comment = @Comment,
        Favorite = @Favorite
    WHERE IDMovieUser = @IDMovieUser
END
GO

CREATE PROCEDURE deleteMovieUser
    @IDMovieUser INT
AS
BEGIN
    DELETE FROM Movie_User WHERE IDMovieUser = @IDMovieUser
END
GO

CREATE PROCEDURE selectMovieUser
    @IDMovieUser INT
AS
BEGIN
    SELECT * FROM Movie_User WHERE IDMovieUser = @IDMovieUser
END
GO

CREATE PROCEDURE selectMovieUsers
AS
BEGIN
    SELECT * FROM Movie_User
END
GO

-- MOVIEGENRE
CREATE PROCEDURE createMovieGenre
    @MovieID INT,
    @GenreID INT,
    @IDMovieGenre INT OUTPUT
AS
BEGIN
    INSERT INTO MovieGenre VALUES (@MovieID, @GenreID)
    SET @IDMovieGenre = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE deleteMovieGenre
    @IDMovieGenre INT
AS
BEGIN
    DELETE FROM MovieGenre WHERE IDMovieGenre = @IDMovieGenre
END
GO

CREATE PROCEDURE selectMovieGenres
AS
BEGIN
    SELECT * FROM MovieGenre
END
GO

-- MOVIEACTOR
CREATE PROCEDURE createMovieActor
    @MovieID INT,
    @ActorID INT,
    @IDMovieActor INT OUTPUT
AS
BEGIN
    INSERT INTO MovieActor VALUES (@MovieID, @ActorID)
    SET @IDMovieActor = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE deleteMovieActor
    @IDMovieActor INT
AS
BEGIN
    DELETE FROM MovieActor WHERE IDMovieActor = @IDMovieActor
END
GO

CREATE PROCEDURE selectMovieActors
AS
BEGIN
    SELECT * FROM MovieActor
END
GO

-- MOVIEDIRECTOR
CREATE PROCEDURE createMovieDirector
    @MovieID INT,
    @DirectorID INT,
    @IDMovieDirector INT OUTPUT
AS
BEGIN
    INSERT INTO MovieDirector VALUES (@MovieID, @DirectorID)
    SET @IDMovieDirector = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE deleteMovieDirector
    @IDMovieDirector INT
AS
BEGIN
    DELETE FROM MovieDirector WHERE IDMovieDirector = @IDMovieDirector
END
GO

CREATE PROCEDURE selectMovieDirectors
AS
BEGIN
    SELECT * FROM MovieDirector
END
GO
