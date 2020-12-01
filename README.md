# pokeronline
1	User (che è anche il giocatore) coi campi classici (nome, cognome, username, password, data registrazione, stato), esperienzaAccumulata che indica una specie di punteggio attribuito dal sistema per cercare di livellare le abilità dei giocatori e far quindi disputare partite alla pari, creditoAccumulato che è in pratica il credito accumulato in termini di soldi
2	Tavolo (sarebbe la partita) coi campi esperienzaMin cioè il minimo dell’esperienzaAccumulata che gli utenti devono possedere per poter giocare a quel tavolo, cifraMinima  che è il minimo valore di denaro che si deve possedere per giocare a quel tavolo, denominazione …., data creazione…..
Tavolo ha un set di User (i giocatori) ed un private User creatore che è colui che ha creato il tavolo.
Role = ADMIN (fa tutto), PLAYER(solo play management), SPECIAL_PLAYER(play e gestione tavolo).

Utente ruolo molti a molti
-prima di disabilitare un player devo sganciarlo dal tavolo se è in un tavolo
-se non hai ruolo puoi accedere ma non puoi fare niente

Dopo la login (se non sei registrato c’è il link che porta al form di registrazione e ti mette nello stato CREATO e poi un admin deve passarti nello stato ATTIVO) si atterra su una homepage con 3 macrofunzionalità: Gestione Tavolo (solo), Play Management, Gestione Amministrazione (solo se sono un admin).
Analizziamole una ad una:

Gestione Tavolo (2 tasti: inserisci nuovo e ricerca).

Se agisco su inserisci NUOVO:….CRUD
Se agisco su RICERCA:
	ovviamente sul conferma deve ricercare solo nei propri per fare CRUD
	cioè solo i tavoli in cui l’utente è il creatore.
	Non può cancellare né modificare finché ci sono giocatori a quel 
	Tavolo.


Play management (3 tasti):

Primo tasto: COMPRA CREDITO





Molto semplice.
Secondo tasto: GO TO LAST GAME.
Compare solo se io sono ancora nel set di qualche tavolo. Questo perché io potrei anche chiudere il browser e non fare logout quindi io sono ancora in una partita. Infatti il container dopo un po’ invalida la sessione ma mica può anche ‘svuotare’ ogni volta i set. Quindi io per abbandonare una partita devo accedere all’apposita funzionalità, cioè il Lascia della simulazione partita.
Nel caso in cui compaia questo tasto, sparisce quello di ricerca. Questo significa che ogni volta che viene invocata la action del gioca devo controllare se sono legato ad un tavolo e farlo presente.
Terzo tasto: RICERCA che porta alla pagina:
	ovviamente usare combo di partecipanti.

	Quindi è come se io entro e voglio giocare al tavolo dove sta giocando
U	un mio amico. Ho la possibilità di farlo.




Sul conferma ricerca solo i tavoli in cui esperienza minima <= esperienza accumulata.
Vien fuori una lista di tavoli con accanto il tasto gioca. (Gestire a piacere il caso di credito < cifra minima).
Premendo gioca:
	








Sul gioca:
la pagina si ricarica ma nel frattempo bisogna trovare un modo per simulare la partita:
double segno = Math.random();
se segno >=0.5 segno positivo, negativo altrimenti.
Int somma=(int)Math.random()*1000
Tot = segno*somma
Questa cifra, che può essere positiva o negativa, va aggiunta (o sottratta) al campo creditoAcc dell’utente che sta giocando.
Se si arriva a importo < 0 non va un valore negativo ma a zero. A quel punto a piacere si intervenire con un messaggio di credito esaurito e la possibilità di tornare alla home.
Sul lascia:
 il sistema fa il ++ di esperienza. Qui si individua immediatamente un bug cioè qualcuno per accumulare esperienza potrebbe entrare e uscire n volte senza giocare. Ma a noi non importa…

