-- phpMyAdmin SQL Dump
-- version 4.6.3
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Úte 16. led 2018, 23:57
-- Verze serveru: 5.6.26
-- Verze PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `kivbook`
--
CREATE DATABASE IF NOT EXISTS `kivbook` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `kivbook`;

--
-- Vyprázdnit tabulku před vkládáním `conversation`
--

TRUNCATE TABLE `conversation`;
--
-- Vypisuji data pro tabulku `conversation`
--

INSERT INTO `conversation` (`id`, `first_user_id`, `second_user_id`, `last_message_id`) VALUES
(1, 9, 5, 56),
(2, 10, 5, 53),
(3, 11, 5, 54),
(4, 8, 5, 55);

--
-- Vyprázdnit tabulku před vkládáním `friend_request`
--

TRUNCATE TABLE `friend_request`;
--
-- Vypisuji data pro tabulku `friend_request`
--

INSERT INTO `friend_request` (`id`, `created_date`, `responded_date`, `friend_request_state`, `sender_id`, `receiver_id`) VALUES
(1, '2018-01-07', '2018-01-07', 'ACCEPTED', 8, 5),
(2, '2018-01-07', '2018-01-07', 'ACCEPTED', 9, 5),
(3, '2018-01-07', '2018-01-07', 'ACCEPTED', 10, 5),
(4, '2018-01-07', '2018-01-07', 'ACCEPTED', 11, 5),
(5, '2018-01-07', '2018-01-07', 'ACCEPTED', 12, 5),
(6, '2018-01-07', '2018-01-07', 'ACCEPTED', 13, 5),
(7, '2018-01-07', '2018-01-07', 'ACCEPTED', 14, 5),
(8, '2018-01-07', '2018-01-07', 'ACCEPTED', 15, 5),
(9, '2018-01-07', '2018-01-07', 'PENDING', 6, 5),
(10, '2018-01-07', '2018-01-07', 'PENDING', 7, 5);

--
-- Vyprázdnit tabulku před vkládáním `message`
--

TRUNCATE TABLE `message`;
--
-- Vypisuji data pro tabulku `message`
--

INSERT INTO `message` (`id`, `text`, `timestamp`, `state`, `sender_id`, `receiver_id`) VALUES
(53, 'Už jsi dneska jedl?', '2016-07-06 17:10:00', 'SENT', 10, 5),
(54, 'Hi, you just won 1 MILLION DOLLARS!!!', '2016-07-06 17:10:00', 'SENT', 11, 5),
(55, 'Tahle zpráva je už dávno přečtená.', '2016-07-06 17:10:00', 'READ', 8, 5),
(56, 'Čau, já jsem hustej uživatel.', '2017-01-01 11:47:00', 'READ', 9, 5),
(57, 'Čau, já jsem Pepa. Jak se máš?', '2017-01-01 12:21:00', 'READ', 5, 9),
(58, 'Ale jo, dá se. Co ty?', '2017-01-01 12:25:00', 'READ', 9, 5),
(59, 'No nic moc. Už musim jít, tak čau.', '2017-01-01 12:30:00', 'READ', 5, 9),
(60, 'Čau.', '2017-01-01 12:32:00', 'READ', 9, 5),
(61, 'Čau.', '2017-01-02 06:30:00', 'READ', 9, 5),
(62, 'Tak co, jak se máš?', '2017-01-02 06:31:00', 'READ', 9, 5),
(63, 'Tady ti posílám vážně dlouhou zprávu: Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2017-01-02 06:35:00', 'READ', 9, 5),
(65, 'Dík no.', '2017-01-02 07:00:00', 'READ', 5, 9);

--
-- Vyprázdnit tabulku před vkládáním `post`
--

TRUNCATE TABLE `post`;
--
-- Vypisuji data pro tabulku `post`
--

INSERT INTO `post` (`id`, `date_posted`, `time_posted`, `text`, `visibility`, `owner_id`) VALUES
(2, '2017-10-12', '00:00:00', 'Tohle je fakt dobrá sociální síť.', 'REGISTERED_USERS', 5),
(3, '2017-10-02', '00:00:00', 'Hej lidi, taky máte ten pocit, jako bychom žili na planetě opic?', 'REGISTERED_USERS', 9),
(4, '2018-01-11', '20:54:03', 'Testovací post.', 'REGISTERED_USERS', 5),
(5, '2018-01-15', '00:52:57', 'Jiný testovací post.', 'REGISTERED_USERS', 5),
(6, '2018-01-16', '00:07:46', 'Další post. Zobrazí se správně?', 'REGISTERED_USERS', 5),
(7, '2018-01-16', '01:01:15', 'Tenhle post uvidím jenom já.', 'OWNER', 5),
(8, '2018-01-16', '01:01:47', 'Tenhle post uvidí všichni registrovaní uživatelé.', 'REGISTERED_USERS', 9),
(9, '2018-01-16', '20:45:47', 'Two exquisite objection delighted deficient yet its contained. Cordial because are account evident its subject but eat. Can properly followed learning prepared you doubtful yet him. Over many our good lady feet ask that. Expenses own moderate day fat trifling stronger sir domestic feelings. Itself at be answer always exeter up do. Though or my plenty uneasy do. Friendship so considered remarkably be to sentiments. Offered mention greater fifteen one promise because nor. Why denoting speaking fat indulged saw dwelling raillery. Friendship contrasted solicitude insipidity in introduced literature it. He seemed denote except as oppose do spring my. Between any may mention evening age shortly can ability regular. He shortly sixteen of colonel colonel evening cordial to. Although jointure an my of mistress servants am weddings. Age why the therefore education unfeeling for arranging. Above again money own scale maids ham least led. Returned settling produced strongly ecstatic use yourself ', 'OWNER_FRIENDS', 5),
(10, '2018-01-16', '20:46:11', '?', 'OWNER_FRIENDS', 5),
(11, '2018-01-16', '20:46:21', 'sdfsdfsdf', 'REGISTERED_USERS', 5),
(12, '2018-01-16', '20:46:29', 'sdfsdf', 'OWNER_FRIENDS', 5),
(13, '2018-01-16', '20:55:31', 'Friends only', 'OWNER_FRIENDS', 5),
(14, '2018-01-16', '22:04:40', 'Two exquisite objection delighted deficient yet its contained. Cordial because are account evident its subject but eat. Can properly followed learning prepared you doubtful yet him. Over many our good lady feet ask that. Expenses own moderate day fat trifling stronger sir domestic feelings. Itself at be answer always exeter up do. Though or my plenty uneasy do. Friendship so considered remarkably be to sentiments. Offered mention greater fifteen one promise because nor. Why denoting speaking fat indulged saw dwelling raillery. Friendship contrasted solicitude insipidity in introduced literature it. He seemed denote except as oppose do spring my. Between any may mention evening age shortly can ability regular. He shortly sixteen of colonel colonel evening cordial to. Although jointure an my of mistress servants am weddings. Age why the therefore education unfeeling for arranging. Above again money own scale maids ham least led. Returned settling produced strongly ecstatic use yourself w', 'REGISTERED_USERS', 5);

--
-- Vyprázdnit tabulku před vkládáním `kivbook_image`
--

TRUNCATE TABLE `kivbook_image`;
--
-- Vypisuji data pro tabulku `kivbook_image`
--

INSERT INTO `kivbook_image` (`id`, `image_data`) VALUES
(1, 0x6956424f5277304b47676f414141414e53556845556741414149414141414341434149414141424d585061634141414141584e535230494172733463365141414141526e51553142414143786a777638595155414141414a6345685a6377414144734d4141413744416364767147514141414c755355524256486865375a4c74596f4d67454154372f692b64496c787350697842744d3764646563584842725a6e587a6442496f45774567416a4154415341434d424d424941497745774567416a4154415341434d424d424941497745774567416a4154415341434d4c7746665739685a556c7a4573365a2f3664724f6b707267553430336d3149446d5765753047514f7344424865737a6b67456c79764d453044695141426f6878566e63354846796434647a57456a68774961414d473762667770353466755a6c4778466641676f326573624f4b6a61717647776a636d6d41332f707138365864726f4332614a4f5639306b734c7231397036796c34452b6e44527664655a2f45776f75416153526742784c776a6754415341434d424d42636666747a2b3472656673474c6748365663322b46774a4741547075647432775646694241703832473765397344677562773341344574436f62527672704330653252784768496b78557439693449364e487467635267534c636154424e4f30587943544c6633746e6c524f764f4963504d31356f73756f624c694c56762f5743375a2b7873347a744633796c7371616673624f6b4f49325876766356435943524142674a674a454147416d416b514159435944786d2f4f664f504159736c532f59714f382b45723457487062724e75734f497058792f2b357a37702b6d5366445337444e69682b4831634b4337625041352b6e552b6a357644786473487838736952565a736445622f61504f6153435944495031395a385a2b51582f41426c712b5550662f666a592b452b353565726237367073384d6e7848335449645665767a652f3733506a7a457a2f756849737550566651726c666d506f467a7859326e71356c34612b35444948392b3356722b3546666d586a7a79786574786664456a505562526b465a41495953447a414a43494145774567446a4e2b462f614c38674154415341434d424d4b3544466766704e54694e7431612f4c724c694c74746d34346b642b4171323258366a637851615235452b3969734266387449762f6b63654d6c546d7055416b76466d6b7a6c774557623538307341794e354f4d7a6d514142672b79555362456e416d6332326d635144486d4f3552416b36676c43674273414262545a4844515741426851514f7341436c4f776b6f6b414a736451414a6d4f6573377149376b4141593576616c4e516c6f59414a736452674a6d4f4863316b49376b41415943594352414a674d41677078485351524542635641534d424d424941497745774567416a4154415341434d424d424941497745774567416a4154415341434d424d424941497745774567416a4154415341434d424d4249414977456f74397333457373505242717838645941414141415355564f524b35435949493d),
(2, 0x89504e470d0a1a0a0000000d49484452000000800000008008020000004c5cf69c000000017352474200aece1ce90000000467414d410000b18f0bfc6105000000097048597300000ec300000ec301c76fa8640000069849444154785eed9a5192dd280c45b390f99cfdef6c7eb2811ef5d381961f600306cb7ecd292a712421a47bbb2af9c89faf852bcb00679601ce2c039c590638b30c706619e0cc32c099658033cb00679601ce2c039cb9dc80bf7f36e7d773a10459c57fbd13976c5e23f16ff560fedaf5cad6f8f4714c5eb843d05f66c3b4554fea38db031dafe64c66c203a3e61ebbbc4e154f25adf5edcc31601467f68f77bb3b588634c931bae98c2975f97a09ea2b5b691aa38ea183cedbfcc59f323fd2cc1ce087710f8d366028a85b018a84437036fadc39c64db93f4a90a6efb4f2768b1926a143f672a10147a0d638541a0b2fcda0d7834b0c381a0e79e6a036c889f0ea70c243fcb18ec906540c842a1570a10c750518a67406d2d26ddcc3d957774741985d286d87fbbb50aaa80d7a4ed2d2e1f46391f4d5f21c085086ba11d0b10c75916b3d18b7eadb93bdea5334011ec84145e4420fc62d5c67001be7a06226bc94830acb490f3c0d68549ff455f06a0269cb7c0fc62d1f1fcbbdfabd9ec4cd5148bbb09d474e7e2449755371779c04f1b1f455b39e85ac1fcc118843928ebce6e7bb95a38be354d097ec7b3a774e7a811a6f98261067266dc9066bd8bd384e087d263e163ed86c8ba6ee036319f2aa49301b3fa47c6b9c1676b8f0c136068ddf10e6331455b39b5652ae1fa7481c2b3cc61e5b34754f18d15014ae2c689e72fd38451eaebec0945b667b304814e9ae27c0f80612f786590ddfd192d6db958fc9158fd045fb9a6998dda0f147c0c4863d95775229b9e211d2685fd39dc103449f03731b8a4237192024f5a7d5918ef1bc606483c63b3879dda2ad2c24725061f88e26da41299e25296eb99c45a50f7d99d7a0f13e6871da03ba6c21578022c377b4a475299e655bdc723345a5371d19d640a2112e0788f642972de40a50642091d53a1b2cb12d6eb9f946943e74645283c63be0be814417b43090d885520389acdcd9600953dc72cd62a50fed18d3a0f10eb86f20d1052d02442be04280a890953b1bcc622a1ba6f921de970fdb6b0bd12e686120712dbc1d202a64b5ce064b84e2c63b7ae21fe3af1324a38b93f40a1318480851074b369825eaa6bf1d93b6d6486cb445831f00fb04880ab27849934a5ec51517b22f091a0c29060c68f003609f00d148aa4c56ab127f0f952ab58bf1d707d31934f901b08f818492d5a7c583ddd29d46bb0668e66360ab00d1c8390f0a75727fbf45ccbe3e182da0998f81ad02442359a1f6d533142e1fde8f05af0f460b68e66360ab00514b56ae430d5f244535d76ccdef334020111963805ca8bbb3294bfed303f1cf82dd02442da9743d0654b20c48c9aa572169a8a8575f5806a494043c1236a4eb0d78ab5c0644b21a2e0386c06e01a229ed1e84dc326017760b104d59064c82dd0244b3a44a561920485d8d0dcb80590628fb1ee4babf41fcb360b700d12c5901cbaa66042d56175abf41fcb360b700d12c670dd8611910205a22156a8001a516af38730534f349b05880688965c058d82a40749f37b9ce1a50beff2c03fefbe75fbe5a60ab00d17d960129a2fe75060856b4890608c9dfc302a96afaa4a96794fa02891aa26ea70cd8575f7815305d4033f55c60005f8db04f806825030c38545f19f18fd1791e9ce9cc3e01a295381a2090aa668601d273a0fa02894aae34407e61c68066ea196ec049f5053609106d42057c8401c2580fce77639300d1261c0d1034598ffecc0eb161b8fa02892644995d0d8f9ab6182030a941e34d9cf7e02eea2b8f33403923e28d0c1065ae3440605e0389763a74942bb7fbf17fae01820a5aa9e910f505e63690e8e07a0304a63690e825da7078b87002263690e84065b9de0081d90d24ee0db36e21d74ad4e494019554182090bb2b4cb9855c07be06086c6020715798d240a20f770304f63090b81fcc6720711e470304b63190b8134c662031045f030476da42ce1ba649203d847b1a2090f6833912488fc2dd0081cd12487bc00409a40772070314564c207d15bc9a838ab14c37a04e7d8545735031135eca41c50c6e6580c0c639a898036fe4a0621277334060ef32d48d808e65a89bc75c03dad58f20c02e94b6c3fd23a89ec7913e9e0608c85001178ea0ba022ecc66ae01e7d48f20c955f0ea353cc200057966c24b57f2200322a8350efabaf0440322e8d70b5d1ca9d0e7c49493d57f03518fa0fa0ed4e9f318031e46b5385d224af7a5fe3e130d58eaefd3a84fa3944bfd7ddac569376031946580334b50679601ce2c039c590638b30c706619e0cc32c099658033cb00679601ce2c039c590638b30c706619e0cc32c099658033cb00679601ce2c039c5906b8f2f5f53fc507a83f5bf5b1940000000049454e44ae426082),
(3, 0x89504e470d0a1a0a0000000d4948445200000080000000800806000000c33e61cb000000017352474200aece1ce90000000467414d410000b18f0bfc6105000000097048597300000ec200000ec20115284a800000043449444154785eed9ad191da401044c1cec0493809f82313076208c499dc27419c937006aef30da8cd2256da154830b3ddafca85e0c422e837b32bc9eb8f4f5682962fdda3204502902301c89100e448007224003912801c09408e04204702902301c89100e4480072c2dd0d5cafd7dd5619dde82ce35e807ee01f3fbf775b65d687dfddd60549718d4b01d2d0a7045e03a49008675c0980e0e70e3d878920091c0ad00fffcfafbfddd6996f3fbe765bd3c1581843ddc09100fdf0fb618153e53ed02124c1352e04c8b57e0b6aa8da119a919321fd7bcad0f8cc12b811a036fc3effc34bde6fafe5c448c975024601dc5d084230b594821e2227183a11132f1720f7a3d7563fb8570203c23d3246645e3e05f4dbff2b615c0bb8102032d16571731a9872ea0a0387950a5373e863fb8f7d0e0ba1ef0696ba47e9efc2a100355539b56a87f6b7d7d92509d701d2d06a4550271826e41460c1d7848ffd6af72f713c1ebbad7608bd0678262d866f488002163cc2df6c36a747a31521244005167c3ffcf479642440010bbad5f66fb813c0166bcf5ab5d79c72a6a4d3412b84bb1268d40637659cfd7e7f7a4c391c0e97fb14fbf7a65a3f082b80513af4a171726183dd6ed76dad56dbedf64a8016712980512b8191db2f956428f034ecb7b7b79be7d6010ca73fd12c841500a42218784f3ff452d8b9e7ea002fa65602909361acddd7d05f07b4866b018ca91294987bbce8b817c040653f72a8738cd122210400f756afaa7e98500218a86463ecd06bf763279c002969c87d147a1da10548519bbf0fdd0c224702902301c89100e448007224003912809c6604b06b00631786449e2604b0e011be249846680110bc553fae02aa134c23ac00083977f9171248843221ef05a0ea73f4ff964a10f0ab2e4ea80e80aa9e12a4ed8bfdf17e71c17d07e807563adc1a4130a6f3affe145c77803428fc9b03057fc1ad004b57a98d6b9f81cf61c5a500cf6ad1363e4460c59500a84804f32c2001a3086e04c08fffcce053f0b96c12b810e0d5e103fb7c7403165e2e8097f05320018308cdfcaf6060a1cdf9953c0a3a272ecf023c81e05bed0612a00293401d4034890420a72901e65e0032a00e408e0420470290d3940036ffdb3aa0d573f62568460004dffa859bb9097f291841e7be462a84c813ba038c856fd8ebb68fbac13061054075972a5c1d601c8ab3007402714b480114e67c84ed00535bbbba409e700228c47909d901ee5dd8a90bdc42b10814c38412c0aaf7deea1779e83a80a6816bc208a0ea5f861002a86297234c0798b3fa350d5ca05b03004970c6bd000a69594274002dfe9683760a30340d900b209c0ba0f97f79dc7700cdffcb423f05b0af03b406204702902301c891009f30af03244007ebd9866b01740ab83cea00e448007224003912801c09408e04204702902301c89100e448007224003912801c09408e04204702902301c89100e448007224003912801c0940cd6af50f760163ae296458290000000049454e44ae426082);

--
-- Vyprázdnit tabulku před vkládáním `user`
--

TRUNCATE TABLE `user`;
--
-- Vypisuji data pro tabulku `user`
--

INSERT INTO `user` (`id`, `username`, `password_hash`, `email`, `full_name`, `birth_date`, `gender`, `profile_visibility`, `social_identity_provider`, `profile_photo_id`) VALUES
(5, 'user1', '$2a$10$Ny5unXR2FqkvdsK/XTwT1.BwFUCpU8OIVc0ONEiDcvY9PIn6a7v/O', 'user1@kivbook.com', 'Pepa Uživatel', '1954-06-07', 'MALE', 'REGISTERED_USERS', NULL, 1),
(6, 'user2', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user2@email.com', 'Nový kamarád', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 3),
(7, 'user3', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user3@email.com', 'Nová kamarádka', '1965-05-06', 'FEMALE', 'REGISTERED_USERS', NULL, 2),
(8, 'user4', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user4@email.com', 'Kámoš', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 1),
(9, 'user5', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user5@email.com', 'Hustej Uživatel', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 3),
(10, 'user6', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user6@email.com', 'Maminečka', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 2),
(11, 'user7', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user7@email.com', 'Někdo úplně jinej', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 1),
(12, 'user8', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user8@email.com', 'Spolužák Petr', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 3),
(13, 'user9', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user9@email.com', 'Kamarádka Míša', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 2),
(14, 'user10', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user10@email.com', 'Kolega Pavel', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 1),
(15, 'user11', '$2a$10$.7yf.pLoqzhGXbpCrAwSFeBba5rIxarTehr8J8z3UJbFVfI3Dv4F6', 'user11@email.com', 'Klára Nováková', '1965-05-06', 'MALE', 'REGISTERED_USERS', NULL, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
