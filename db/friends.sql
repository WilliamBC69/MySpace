USE [MySpace]
GO

/****** Object:  Table [dbo].[Friends]    Script Date: 3/18/2024 20:02:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Friends](
	[userid1] [int] NOT NULL,
	[userid2] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userid1] ASC,
	[userid2] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Friends]  WITH CHECK ADD FOREIGN KEY([userid1])
REFERENCES [dbo].[Users] ([userid])
GO

ALTER TABLE [dbo].[Friends]  WITH CHECK ADD FOREIGN KEY([userid2])
REFERENCES [dbo].[Users] ([userid])
GO

ALTER TABLE [dbo].[Friends]  WITH CHECK ADD CHECK  (([userid1]<[userid2]))
GO


