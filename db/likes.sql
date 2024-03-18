USE [MySpace]
GO

/****** Object:  Table [dbo].[Likes]    Script Date: 3/18/2024 20:03:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Likes](
	[userid] [int] NOT NULL,
	[postid] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userid] ASC,
	[postid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Likes]  WITH CHECK ADD FOREIGN KEY([postid])
REFERENCES [dbo].[Posts] ([postid])
GO

ALTER TABLE [dbo].[Likes]  WITH CHECK ADD FOREIGN KEY([userid])
REFERENCES [dbo].[Users] ([userid])
GO


