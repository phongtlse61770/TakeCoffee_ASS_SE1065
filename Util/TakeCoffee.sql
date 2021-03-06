USE TakeCoffee
GO
ALTER TABLE [dbo].[Product] DROP CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[OrderProduct] DROP CONSTRAINT [FK_OrderProduct_Product]
GO
ALTER TABLE [dbo].[OrderProduct] DROP CONSTRAINT [FK_OrderProduct_Order]
GO
ALTER TABLE [dbo].[Order] DROP CONSTRAINT [FK_Order_Employee]
GO
ALTER TABLE [dbo].[Order] DROP CONSTRAINT [FK_Order_Customer]
GO
DROP TABLE [dbo].[User]
GO
DROP TABLE [dbo].[Product]
GO
DROP TABLE [dbo].[OrderProduct]
GO
DROP TABLE [dbo].[Order]
GO
DROP TABLE [dbo].[Category]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[createdTime] [datetime] NULL,
	[employeeID] [int] NULL,
	[customerID] [int] NULL,
	[isConfirmed] [bit] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderProduct](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[producID] [int] NULL,
	[orderID] [int] NULL,
	[quantity] [int] NULL,
	[unitPrice] [decimal](19, 4) NULL,
 CONSTRAINT [PK_OrderProduct] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[categoryID] [int] NULL,
	[unitPrice] [decimal](19, 4) NULL,
	[isRemoved] [bit] NOT NULL,
	[isDisabled] [bit] NOT NULL,
	[image] [nvarchar](255) NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NULL,
	[password] [nvarchar](255) NULL,
	[phonenumber] [nchar](10) NULL,
	[isEmployee] [bit] NULL,
	[balance] [decimal](19, 4) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([ID], [name]) VALUES (1, N'Bakery')
INSERT [dbo].[Category] ([ID], [name]) VALUES (2, N'Lunch & Anytime Snacks')
INSERT [dbo].[Category] ([ID], [name]) VALUES (3, N'All-Day Breakfast')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([ID], [createdTime], [employeeID], [customerID], [isConfirmed]) VALUES (1, CAST(N'2017-06-26T08:43:00.847' AS DateTime), 1, 2, 0)
SET IDENTITY_INSERT [dbo].[Order] OFF
SET IDENTITY_INSERT [dbo].[OrderProduct] ON 

INSERT [dbo].[OrderProduct] ([ID], [producID], [orderID], [quantity], [unitPrice]) VALUES (1, 3, 1, 2, CAST(220.0000 AS Decimal(19, 4)))
INSERT [dbo].[OrderProduct] ([ID], [producID], [orderID], [quantity], [unitPrice]) VALUES (2, 6, 1, 1, CAST(55.0000 AS Decimal(19, 4)))
SET IDENTITY_INSERT [dbo].[OrderProduct] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (1, N'Hearty Blueberry Oatmeal', 3, CAST(130.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (2, N'Reduced-Fat Turkey Bacon Breakfast Sandwich', 3, CAST(156.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (3, N'Double-Smoked Bacon, Cheddar & Egg on Croissant Bun (Limited Time)', 3, CAST(220.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (4, N'Wheat Spinach Savory Foldover', 1, CAST(132.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (5, N'Chocolate Croissant', 1, CAST(142.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (6, N'Salted Caramel or Birthday Cake Pop', 2, CAST(50.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (7, N'Ham & Swiss Panini', 2, CAST(122.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (8, N'Old-Fashioned Grilled Cheese', 2, CAST(111.0000 AS Decimal(19, 4)), 0, 0, NULL)
INSERT [dbo].[Product] ([ID], [name], [categoryID], [unitPrice], [isRemoved], [isDisabled], [image]) VALUES (9, N'Protein Bistro Box', 2, CAST(120.0000 AS Decimal(19, 4)), 0, 0, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([ID], [username], [password], [phonenumber], [isEmployee], [balance]) VALUES (1, N'phongtl', N'1234', N'0909261466', 1, CAST(2963.0000 AS Decimal(19, 4)))
INSERT [dbo].[User] ([ID], [username], [password], [phonenumber], [isEmployee], [balance]) VALUES (2, N'lam', N'12345', N'0123456789', 1, CAST(20123.3300 AS Decimal(19, 4)))
INSERT [dbo].[User] ([ID], [username], [password], [phonenumber], [isEmployee], [balance]) VALUES (3, N'admin', N'1234', N'0         ', 1, CAST(0.0000 AS Decimal(19, 4)))
SET IDENTITY_INSERT [dbo].[User] OFF
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([customerID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Employee] FOREIGN KEY([employeeID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Employee]
GO
ALTER TABLE [dbo].[OrderProduct]  WITH CHECK ADD  CONSTRAINT [FK_OrderProduct_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[Order] ([ID])
GO
ALTER TABLE [dbo].[OrderProduct] CHECK CONSTRAINT [FK_OrderProduct_Order]
GO
ALTER TABLE [dbo].[OrderProduct]  WITH CHECK ADD  CONSTRAINT [FK_OrderProduct_Product] FOREIGN KEY([producID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[OrderProduct] CHECK CONSTRAINT [FK_OrderProduct_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
