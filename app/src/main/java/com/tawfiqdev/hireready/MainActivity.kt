package com.tawfiqdev.hireready

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import kotlin.math.roundToInt
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private val backStack = mutableListOf<AppScreen>()
    private var currentScreen: AppScreen? = null

    private val blue = "#2F6FED".toColorInt()
    private val blueDark = "#1E55D9".toColorInt()
    private val surface = "#F7F9FC".toColorInt()
    private val card = Color.WHITE
    private val ink = "#111827".toColorInt()
    private val muted = "#6B7280".toColorInt()
    private val soft = "#EEF3FF".toColorInt()
    private val border = "#E2E8F0".toColorInt()
    private val danger = "#EF4444".toColorInt()
    private val success = "#20B26B".toColorInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )

        container = FrameLayout(this).apply {
            setBackgroundColor(surface)
        }
        setContentView(container)
        ViewCompat.setOnApplyWindowInsetsListener(container) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = goBack()
        })

        show(AppScreen.Login)
    }

    private fun navigate(screen: AppScreen) {
        currentScreen?.takeIf { it != screen }?.let(backStack::add)
        show(screen)
    }

    private fun navigateRoot(screen: AppScreen) {
        backStack.clear()
        show(screen)
    }

    private fun goBack() {
        if (backStack.isNotEmpty()) {
            show(backStack.removeAt(backStack.lastIndex))
            return
        }

        when (currentScreen) {
            AppScreen.Login -> finish()
            AppScreen.Register, AppScreen.ForgotPassword -> navigateRoot(AppScreen.Login)
            AppScreen.Dashboard -> finish()
            else -> navigateRoot(AppScreen.Dashboard)
        }
    }

    private fun show(screen: AppScreen) {
        currentScreen = screen
        when (screen) {
            AppScreen.Login -> showLogin()
            AppScreen.Register -> showRegister()
            AppScreen.ForgotPassword -> showForgotPassword()
            AppScreen.Dashboard -> showDashboard()
            AppScreen.MyCvs -> showMyCvs()
            AppScreen.CreateCv -> showCreateCv()
            AppScreen.PersonalInfo -> showPersonalInfo()
            AppScreen.Summary -> showSummary()
            AppScreen.Experiences -> showExperiences()
            AppScreen.Education -> showEducation()
            AppScreen.Skills -> showSkills()
            AppScreen.Languages -> showLanguages()
            AppScreen.Certifications -> showCertifications()
            AppScreen.Projects -> showProjects()
            AppScreen.Achievements -> showAchievements()
            AppScreen.Interests -> showInterests()
            AppScreen.References -> showReferences()
            AppScreen.Preview -> showPreview()
            AppScreen.Score -> showScore()
            AppScreen.ImportCv -> showImportCv()
            AppScreen.Profile -> showProfile()
            AppScreen.Settings -> showSettings()
            AppScreen.Notifications -> showNotifications()
            AppScreen.Confidentiality -> showConfidentiality()
            AppScreen.HelpCenter -> showHelpCenter()
            AppScreen.Faq -> showFaq()
            AppScreen.ContactSupport -> showContactSupport()
            AppScreen.Terms -> showTerms()
            AppScreen.PrivacyPolicy -> showPrivacyPolicy()
            AppScreen.DeleteAccount -> showDeleteAccount()
        }
    }

    private fun showLogin() = page {
        addTopSpace(20)
        addHeader("Bienvenue !", "Connectez-vous à votre compte", showBack = false)
        addSocialButton("G", "Continuer avec Google")
        addSocialButton("A", "Continuer avec Apple")
        addDividerText("ou")
        addInput("Email", "Entrez votre email")
        addInput("Mot de passe", "Entrez votre mot de passe", password = true)
        addInlineLink("Mot de passe oublié ?", Gravity.START) { navigate(AppScreen.ForgotPassword) }
        addPrimaryButton("Se connecter") { navigateRoot(AppScreen.Dashboard) }
        addSmallCenteredText("Pas de compte ? S'inscrire") { navigate(AppScreen.Register) }
    }

    private fun showRegister() = page {
        addHeader("Créer un compte", "Rejoignez CV Builder")
        addInput("Prénom", "Entrez votre prénom")
        addInput("Nom", "Entrez votre nom")
        addInput("Email", "Entrez votre email")
        addInput("Mot de passe", "Créez un mot de passe", password = true)
        addCheckLine("J'accepte les Conditions d'utilisation et la Politique de confidentialité")
        addPrimaryButton("S'inscrire") { navigateRoot(AppScreen.Dashboard) }
        addSmallCenteredText("Déjà un compte ? Se connecter") { navigateRoot(AppScreen.Login) }
    }

    private fun showForgotPassword() = page {
        addHeader("Mot de passe oublié", "Entrez votre email et nous vous enverrons un lien pour réinitialiser votre mot de passe.")
        addInput("Email", "Entrez votre email")
        addPrimaryButton("Envoyer le lien") { navigateRoot(AppScreen.Login) }
        addInlineLink("Retour à la connexion", Gravity.CENTER) { navigateRoot(AppScreen.Login) }
    }

    private fun showDashboard() = page(bottomNav = true, selected = AppScreen.Dashboard) {
        addDashboardHeader()
        addHeroCard()
        addDashboardRow("Mes CV", "5 CV enregistrés", AppScreen.MyCvs)
        addDashboardRow("Importer un CV", "Importer et améliorer votre CV", AppScreen.ImportCv)
        addDashboardRow("Score CV", "Analysez et améliorez votre CV", AppScreen.Score)
        addDashboardRow("Modèles", "Découvrez nos modèles", AppScreen.Preview)
        addDashboardRow("Lettre de motivation", "Créez des lettres personnalisées", AppScreen.HelpCenter)
    }

    private fun showMyCvs() = page(bottomNav = true, selected = AppScreen.MyCvs) {
        addHeader("Mes CV", showBack = false)
        addSearch("Rechercher un CV")
        addCvRow("Responsable RH", "Modifié le 12/06/2026")
        addCvRow("CV Marketing", "Modifié le 05/06/2026")
        addCvRow("CV Développeur", "Modifié le 20/04/2026")
        addCvRow("CV Commercial", "Modifié le 15/04/2026")
        addCvRow("CV Alternance", "Modifié le 01/04/2026")
        addTopSpace(18)
        addPrimaryButton("+ Créer un nouveau CV") { navigate(AppScreen.CreateCv) }
    }

    private fun showCreateCv() = page {
        addHeader("Comment voulez-vous commencer ?")
        addChoiceCard("À partir de zéro", "Créez votre CV étape par étape") {
            navigate(AppScreen.PersonalInfo)
        }
        addChoiceCard("Choisir un modèle", "Sélectionnez un modèle de CV") {
            navigate(AppScreen.Preview)
        }
        addChoiceCard("Importer un CV", "Nous l'analyserons et l'améliorerons") {
            navigate(AppScreen.ImportCv)
        }
        addFlexibleSpace()
        addSecondaryButton("Annuler") { goBack() }
    }

    private fun showPersonalInfo() = formPage(
        title = "Informations personnelles",
        subtitle = "Commencez avec les bases de votre CV.",
        next = AppScreen.Summary
    ) {
        val photoCard = roundedCard()
        photoCard.gravity = Gravity.CENTER
        photoCard.addView(avatar("CL", 82), linearParams(top = 6.dp, bottom = 10.dp))
        photoCard.addView(secondaryButton("Remplacer la photo") {}, linearParams(width = 190.dp))
        addView(photoCard, linearParams(bottom = 18.dp))

        addInput("Prénom *", "Camille", "Camille")
        addInput("Nom *", "Laurent", "Laurent")
        addInput("Titre du poste *", "Responsable des Ressources Humaines", "Responsable des Ressources Humaines")
        addChoiceRow("Informations complémentaires", "Téléphone, adresse, site web")
    }

    private fun showSummary() = formPage(
        title = "Résumé professionnel",
        subtitle = "Présentez-vous en quelques lignes.",
        next = AppScreen.Experiences
    ) {
        addInput(
            "Résumé professionnel *",
            "Votre résumé",
            "Professionnelle des ressources humaines avec plus de 6 ans d'expérience dans le recrutement, la gestion des talents et le développement RH.",
            multiline = true
        )
        addInput(
            "Objectif professionnel",
            "Votre objectif",
            "Évoluer vers un poste de Responsable RH senior pour accompagner la croissance et la transformation des organisations.",
            multiline = true
        )
    }

    private fun showExperiences() = formPage(
        title = "Expériences professionnelles",
        subtitle = "Ajoutez vos expériences.",
        next = AppScreen.Education
    ) {
        addEntryCard("Responsable RH", listOf("Crompa Acta", "Mars 2021 - Présent"))
        addEntryCard("HR Business Partner", listOf("NextGen", "Janvier 2016 - Février 2021"))
        addEntryCard("Chargée RH", listOf("Selftone Conseil", "Sept. 2016 - Déc. 2018"))
        addAddLink("+ Ajouter une expérience")
    }

    private fun showEducation() = formPage(
        title = "Formation",
        subtitle = "Ajoutez vos diplômes et formations.",
        next = AppScreen.Skills
    ) {
        addEntryCard("Master en Ressources Humaines", listOf("Université Lyon 3", "2014 - 2016"))
        addEntryCard("Licence Gestion des RH", listOf("Université Lyon 3", "2011 - 2014"))
        addEntryCard("Baccalauréat ES", listOf("Lycée Saint-Exupéry", "2011"))
        addAddLink("+ Ajouter une formation")
    }

    private fun showSkills() = formPage(
        title = "Compétences",
        subtitle = "Ajoutez vos compétences et évaluez votre niveau.",
        next = AppScreen.Languages
    ) {
        addLevelRow("Gestion des talents", "Avancé")
        addLevelRow("Relations sociales", "Avancé")
        addLevelRow("Droit du travail", "Avancé")
        addLevelRow("Recrutement", "Avancé")
        addLevelRow("SIRH", "Intermédiaire")
        addAddLink("+ Ajouter une compétence")
    }

    private fun showLanguages() = formPage(
        title = "Langues",
        subtitle = "Indiquez vos langues et votre niveau.",
        next = AppScreen.Certifications
    ) {
        addLevelRow("Français", "Langue maternelle")
        addLevelRow("Anglais", "Courant (B2)")
        addLevelRow("Espagnol", "Intermédiaire (B1)")
        addAddLink("+ Ajouter une langue")
    }

    private fun showCertifications() = formPage(
        title = "Certifications",
        subtitle = "Ajoutez vos certifications professionnelles.",
        next = AppScreen.Projects
    ) {
        addEntryCard("Certification Cegos", listOf("Management RH", "2022"))
        addEntryCard("Certification Scrum", listOf("Scrum Master", "2021"))
        addEntryCard("Formation LinkedIn", listOf("People Analytics", "2020"))
        addAddLink("+ Ajouter une certification")
    }

    private fun showProjects() = formPage(
        title = "Projets",
        subtitle = "Présentez vos projets clés.",
        next = AppScreen.Achievements
    ) {
        addEntryCard("Mise en place d'un SIRH", listOf("Déploiement d'un nouvel outil SIRH pour 500 collaborateurs."))
        addEntryCard("Programme Bien-être", listOf("Conception et lancement d'un programme bien-être au travail."))
        addAddLink("+ Ajouter un projet")
    }

    private fun showAchievements() = formPage(
        title = "Réalisations",
        subtitle = "Mettez en avant vos réalisations.",
        next = AppScreen.Interests
    ) {
        addChoiceRow("Réduction du turnover de 15% en 2 ans.", "")
        addChoiceRow("Mise en place d'un processus de recrutement agile.", "")
        addChoiceRow("Accompagnement de plus de 200 collaborateurs.", "")
        addAddLink("+ Ajouter une réalisation")
    }

    private fun showInterests() = formPage(
        title = "Centres d'intérêt",
        subtitle = "Parlez-nous de vos passions.",
        next = AppScreen.References
    ) {
        val chips = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.VERTICAL
        }
        chips.addView(chipRow("Lecture", "Voyage", "Yoga"))
        chips.addView(chipRow("Course à pied", "Photographie"))
        addView(chips, linearParams(bottom = 24.dp))
        addAddLink("+ Ajouter un centre d'intérêt")
    }

    private fun showReferences() = formPage(
        title = "Références",
        subtitle = "Ajoutez vos références professionnelles.",
        next = AppScreen.Preview
    ) {
        addEntryCard("Sophie Martin", listOf("DRH - Crompa Acta", "sophie.martin@acta.com", "06 12 34 56 78"))
        addEntryCard("Julien Bernard", listOf("Directeur Général - NextGen", "julien.bernard@nextgen.com", "06 23 45 67 89"))
        addAddLink("+ Ajouter une référence")
    }

    private fun showPreview() = page {
        addHeader("Aperçu de votre CV")
        addTabHeader("Aperçu", "PDF")
        val preview = roundedCard()
        preview.setPadding(18.dp, 18.dp, 18.dp, 18.dp)
        val profileRow = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        profileRow.addView(avatar("CL", 54), linearParams(width = 54.dp, height = 54.dp, end = 12.dp))
        val nameBlock = LinearLayout(this@MainActivity).apply { orientation = LinearLayout.VERTICAL }
        nameBlock.addView(text("Camille Laurent", 16f, ink, true))
        nameBlock.addView(text("Responsable des Ressources Humaines", 11f, muted))
        nameBlock.addView(text("camille.laurent@email.com", 10f, blue))
        profileRow.addView(nameBlock, linearParams(width = 0, weight = 1f))
        preview.addView(profileRow, linearParams(bottom = 18.dp))
        preview.addView(cvPreviewSection("Profil", "Professionnelle RH avec une solide expérience en recrutement et développement des talents."))
        preview.addView(cvPreviewSection("Expériences", "Responsable RH - Crompa Acta\nHR Business Partner - NextGen"))
        preview.addView(cvPreviewSection("Formation", "Master en Ressources Humaines - Université Lyon 3"))
        addView(preview, linearParams(bottom = 18.dp))
        addButtonRow("Modifier", "Télécharger", { navigate(AppScreen.PersonalInfo) }, { navigate(AppScreen.Score) })
    }

    private fun showScore() = page {
        addHeader("Score de votre CV")
        addTopSpace(18)
        val circle = TextView(this@MainActivity).apply {
            text = "87\n/100"
            gravity = Gravity.CENTER
            textSize = 24f
            setTextColor(success)
            typeface = Typeface.DEFAULT_BOLD
            background = oval(Color.TRANSPARENT, success, 5.dp)
        }
        addView(circle, linearParams(width = 132.dp, height = 132.dp, bottom = 18.dp).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        })
        addCenteredText("Excellent ! Votre CV est très fort.", 16f, ink, true)
        addCenteredText("Voici quelques suggestions pour l'améliorer encore.", 13f, muted, false)
        addScoreLine("Contenu", "90/100")
        addScoreLine("Présentation", "85/100")
        addScoreLine("Compétences", "85/100")
        addScoreLine("Impact", "90/100")
        addPrimaryButton("Voir les recommandations") { navigate(AppScreen.Preview) }
    }

    private fun showImportCv() = page(bottomNav = true, selected = AppScreen.ImportCv) {
        addHeader("Importer un CV", "Gagnez du temps en important votre ancien CV.")
        val upload = roundedCard()
        upload.gravity = Gravity.CENTER
        upload.minimumHeight = 230.dp
        upload.addView(text("Cloud", 15f, blue, true), linearParams(bottom = 10.dp))
        upload.addView(text("Déposez votre fichier ici", 16f, ink, true), linearParams(bottom = 8.dp))
        upload.addView(text("ou", 13f, muted), linearParams(bottom = 10.dp))
        upload.addView(secondaryButton("Parcourir") {}, linearParams(width = 140.dp))
        addView(upload, linearParams(bottom = 18.dp))
        addSmallText("Formats acceptés : PDF, DOCX, TXT\nTaille max : 10 Mo")
    }

    private fun showProfile() = page(bottomNav = true, selected = AppScreen.Profile) {
        addProfileHeader()
        addChoiceRow("Informations personnelles", "Camille Laurent", AppScreen.PersonalInfo)
        addChoiceRow("Abonnement", "Premium")
        addChoiceRow("Mes CV", "5 documents", AppScreen.MyCvs)
        addChoiceRow("Mes lettres", "3 lettres")
        addChoiceRow("Paramètres", "Compte, préférences et sécurité", AppScreen.Settings)
        addChoiceRow("Centre d'aide", "FAQ et contact support", AppScreen.HelpCenter)
        addDangerButton("Déconnexion") { navigateRoot(AppScreen.Login) }
    }

    private fun showSettings() = page {
        addHeader("Paramètres")
        addSectionTitle("Compte")
        addChoiceRow("Modifier le mot de passe", "")
        addChoiceRow("Email", "camille.laurent@email.com")
        addChoiceRow("Langue", "Français")
        addSectionTitle("Préférences")
        addChoiceRow("Thème", "Clair")
        addChoiceRow("Notifications", "", AppScreen.Notifications)
        addChoiceRow("Confidentialité", "Données, cookies et politique", AppScreen.Confidentiality)
        addSectionTitle("Autres")
        addChoiceRow("Conditions d'utilisation", "", AppScreen.Terms)
        addDangerRow("Supprimer mon compte") { navigate(AppScreen.DeleteAccount) }
    }

    private fun showNotifications() = page {
        addHeader("Notifications")
        addSwitchRow("Nouveautés et conseils", "", true)
        addSwitchRow("Rappels et suivis", "", true)
        addSwitchRow("Offres et promotions", "", false)
        addSwitchRow("Mises à jour produit", "Recevez les dernières mises à jour", true)
    }

    private fun showConfidentiality() = page {
        addHeader("Confidentialité")
        addChoiceRow("Politique de confidentialité", "", AppScreen.PrivacyPolicy)
        addChoiceRow("Gestion des données", "")
        addChoiceRow("Consentement des cookies", "")
        addChoiceRow("Données personnelles", "")
        addChoiceRow("Télécharger mes données", "")
        addChoiceRow("Supprimer mes données", "", AppScreen.DeleteAccount)
    }

    private fun showHelpCenter() = page(bottomNav = true, selected = AppScreen.Profile) {
        addHeader("Centre d'aide", showBack = true, actionLabel = "?") { navigate(AppScreen.Faq) }
        addSearch("Rechercher une aide")
        addChoiceRow("Créer un CV", "")
        addChoiceRow("Modifier un CV", "")
        addChoiceRow("Exporter un CV", "")
        addChoiceRow("Compte et abonnement", "")
        addChoiceRow("Résoudre un problème", "")
        addChoiceRow("Questions fréquentes", "", AppScreen.Faq)
        addChoiceRow("Contact support", "", AppScreen.ContactSupport)
    }

    private fun showFaq() = page {
        addHeader("Questions fréquentes")
        addChoiceRow("Comment créer un CV ?", "")
        addChoiceRow("Puis-je importer mon ancien CV ?", "")
        addChoiceRow("Comment supprimer mon compte ?", "")
        addChoiceRow("Mon CV est-il confidentiel ?", "")
        addChoiceRow("Quels formats d'export sont disponibles ?", "")
        addTopSpace(24)
        addCenteredText("Vous ne trouvez pas votre réponse ?", 13f, muted, false)
        addInlineLink("Contactez-nous", Gravity.CENTER) { navigate(AppScreen.ContactSupport) }
    }

    private fun showContactSupport() = page {
        addHeader("Contactez-nous", "Notre équipe est là pour vous aider.")
        addInput("Sujet", "Sélectionnez un sujet")
        addInput("Votre message", "Décrivez votre problème...", multiline = true)
        addChoiceRow("Joindre un fichier", "optionnel")
        addPrimaryButton("Envoyer") { navigate(AppScreen.HelpCenter) }
        addCenteredText("Réponse sous 24h ouvrées", 12f, muted, false)
    }

    private fun showTerms() = legalPage(
        title = "Conditions d'utilisation",
        updatedAt = "Dernière mise à jour : 01/06/2026",
        sections = listOf(
            "1. Introduction",
            "2. Utilisation du service",
            "3. Comptes utilisateurs",
            "4. Abonnements et paiements",
            "5. Propriété intellectuelle"
        )
    )

    private fun showPrivacyPolicy() = legalPage(
        title = "Politique de confidentialité",
        updatedAt = "Dernière mise à jour : 01/06/2026",
        sections = listOf(
            "1. Informations collectées",
            "2. Utilisation des informations",
            "3. Partage des informations",
            "4. Sécurité des données",
            "5. Vos droits"
        )
    )

    private fun showDeleteAccount() = page {
        addHeader("Supprimer mon compte")
        val warning = roundedCard()
        warning.gravity = Gravity.CENTER
        warning.setPadding(18.dp, 24.dp, 18.dp, 24.dp)
        warning.addView(text("!", 42f, danger, true), linearParams(bottom = 10.dp))
        warning.addView(text("Êtes-vous sûr de vouloir supprimer votre compte ?", 18f, ink, true).apply {
            gravity = Gravity.CENTER
        }, linearParams(bottom = 12.dp))
        warning.addView(text("Cette action est irréversible. Toutes vos données seront définitivement supprimées.", 13f, muted).apply {
            gravity = Gravity.CENTER
        })
        addView(warning, linearParams(bottom = 18.dp))
        addInput("", "Entrez SUPPRIMER pour confirmer")
        addDangerButton("Supprimer mon compte") { navigateRoot(AppScreen.Login) }
        addSecondaryButton("Annuler") { goBack() }
    }

    private fun formPage(
        title: String,
        subtitle: String,
        next: AppScreen,
        content: LinearLayout.() -> Unit
    ) = page {
        addHeader(title, subtitle)
        content()
        addFlexibleSpace()
        addButtonRow("Retour", "Continuer", { goBack() }, { navigate(next) })
    }

    private fun legalPage(title: String, updatedAt: String, sections: List<String>) = page {
        addHeader(title, updatedAt)
        sections.forEach { addChoiceRow(it, "") }
        addFlexibleSpace()
        addSecondaryButton("J'ai compris") { goBack() }
    }

    private fun page(
        bottomNav: Boolean = false,
        selected: AppScreen? = null,
        content: LinearLayout.() -> Unit
    ) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(surface)
        }
        val scroll = ScrollView(this).apply {
            isFillViewport = true
            overScrollMode = View.OVER_SCROLL_IF_CONTENT_SCROLLS
        }
        val body = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20.dp, 16.dp, 20.dp, 22.dp)
        }
        body.content()
        scroll.addView(body, ViewGroup.LayoutParams(match, wrap))
        root.addView(scroll, LinearLayout.LayoutParams(match, 0, 1f))
        if (bottomNav) {
            root.addView(bottomNavigation(selected ?: currentScreen ?: AppScreen.Dashboard))
        }

        container.removeAllViews()
        container.addView(root, FrameLayout.LayoutParams(match, match))
    }

    private fun LinearLayout.addHeader(
        title: String,
        subtitle: String? = null,
        showBack: Boolean = true,
        actionLabel: String? = null,
        action: (() -> Unit)? = null
    ) {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        if (showBack) {
            row.addView(iconButton("<") { goBack() }, linearParams(width = 36.dp, height = 36.dp, end = 8.dp))
        }
        val titleBlock = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.VERTICAL
        }
        titleBlock.addView(text(title, 22f, ink, true))
        subtitle?.takeIf { it.isNotBlank() }?.let {
            titleBlock.addView(text(it, 13f, muted), linearParams(top = 4.dp))
        }
        row.addView(titleBlock, linearParams(width = 0, weight = 1f))
        if (actionLabel != null && action != null) {
            row.addView(iconButton(actionLabel, action), linearParams(width = 36.dp, height = 36.dp, start = 8.dp))
        }
        addView(row, linearParams(bottom = 22.dp))
    }

    private fun LinearLayout.addDashboardHeader() {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val copy = LinearLayout(this@MainActivity).apply { orientation = LinearLayout.VERTICAL }
        copy.addView(text("Bonjour Camille", 22f, ink, true))
        copy.addView(text("Que souhaitez-vous faire aujourd'hui ?", 13f, muted), linearParams(top = 3.dp))
        row.addView(copy, linearParams(width = 0, weight = 1f))
        row.addView(iconButton("!") { navigate(AppScreen.Notifications) }, linearParams(width = 38.dp, height = 38.dp))
        addView(row, linearParams(bottom = 18.dp))
    }

    private fun LinearLayout.addHeroCard() {
        val hero = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(18.dp, 18.dp, 18.dp, 18.dp)
            background = rounded(blue, 16.dp)
            setOnClickListener { navigate(AppScreen.CreateCv) }
        }
        val copy = LinearLayout(this@MainActivity).apply { orientation = LinearLayout.VERTICAL }
        copy.addView(text("Créer un nouveau CV", 17f, Color.WHITE, true))
        copy.addView(text("Commencez un CV à partir de zéro", 12f, Color.parseColor("#DCE8FF")), linearParams(top = 4.dp))
        hero.addView(copy, linearParams(width = 0, weight = 1f))
        val plus = TextView(this@MainActivity).apply {
            text = "+"
            gravity = Gravity.CENTER
            textSize = 24f
            setTextColor(blue)
            typeface = Typeface.DEFAULT_BOLD
            background = rounded(Color.WHITE, 999.dp)
        }
        hero.addView(plus, linearParams(width = 44.dp, height = 44.dp, start = 14.dp))
        addView(hero, linearParams(bottom = 18.dp))
    }

    private fun LinearLayout.addProfileHeader() {
        val header = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        header.addView(avatar("CL", 64), linearParams(width = 64.dp, height = 64.dp, end = 12.dp))
        val copy = LinearLayout(this@MainActivity).apply { orientation = LinearLayout.VERTICAL }
        copy.addView(text("Camille Laurent", 20f, ink, true))
        copy.addView(text("camille.laurent@email.com", 12f, muted), linearParams(top = 2.dp))
        header.addView(copy, linearParams(width = 0, weight = 1f))
        header.addView(iconButton("*") { navigate(AppScreen.Settings) }, linearParams(width = 38.dp, height = 38.dp))
        addView(text("Mon profil", 22f, ink, true), linearParams(bottom = 16.dp))
        addView(header, linearParams(bottom = 22.dp))
    }

    private fun LinearLayout.addSocialButton(mark: String, label: String) {
        val button = roundedCard()
        button.orientation = LinearLayout.HORIZONTAL
        button.gravity = Gravity.CENTER_VERTICAL
        button.setPadding(14.dp, 12.dp, 14.dp, 12.dp)
        button.addView(text(mark, 17f, ink, true).apply {
            gravity = Gravity.CENTER
        }, linearParams(width = 32.dp, height = 32.dp, end = 10.dp))
        button.addView(text(label, 14f, ink, true), linearParams(width = 0, weight = 1f))
        addView(button, linearParams(bottom = 10.dp))
    }

    private fun LinearLayout.addDividerText(label: String) {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        row.addView(View(this@MainActivity).apply { setBackgroundColor(border) }, linearParams(width = 0, height = 1.dp, weight = 1f))
        row.addView(text(label, 12f, muted), linearParams(start = 12.dp, end = 12.dp))
        row.addView(View(this@MainActivity).apply { setBackgroundColor(border) }, linearParams(width = 0, height = 1.dp, weight = 1f))
        addView(row, linearParams(top = 8.dp, bottom = 16.dp))
    }

    private fun LinearLayout.addInput(
        label: String,
        hint: String,
        value: String = "",
        multiline: Boolean = false,
        password: Boolean = false
    ) {
        if (label.isNotBlank()) {
            addView(text(label, 13f, ink, true), linearParams(bottom = 6.dp))
        }
        val edit = EditText(this@MainActivity).apply {
            setText(value)
            this.hint = hint
            textSize = 14f
            setTextColor(ink)
            setHintTextColor(Color.parseColor("#9CA3AF"))
            setPadding(14.dp, 0, 14.dp, 0)
            background = rounded(card, 10.dp, border, 1.dp)
            if (multiline) {
                minLines = 5
                gravity = Gravity.TOP or Gravity.START
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                setPadding(14.dp, 12.dp, 14.dp, 12.dp)
            } else {
                isSingleLine = true
                inputType = InputType.TYPE_CLASS_TEXT
                minHeight = 48.dp
            }
            if (password) {
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        addView(edit, linearParams(height = if (multiline) 136.dp else 48.dp, bottom = 14.dp))
    }

    private fun LinearLayout.addSearch(hint: String) {
        addInput("", hint)
    }

    private fun LinearLayout.addInlineLink(label: String, gravityValue: Int, action: () -> Unit) {
        addView(text(label, 13f, blue, true).apply {
            gravity = gravityValue
            setOnClickListener { action() }
        }, linearParams(top = 2.dp, bottom = 14.dp))
    }

    private fun LinearLayout.addSmallCenteredText(label: String, action: () -> Unit) {
        addView(text(label, 12f, muted).apply {
            gravity = Gravity.CENTER
            setOnClickListener { action() }
        }, linearParams(top = 14.dp))
    }

    private fun LinearLayout.addSmallText(label: String) {
        addView(text(label, 12f, muted).apply { gravity = Gravity.CENTER })
    }

    private fun LinearLayout.addCenteredText(label: String, size: Float, color: Int, bold: Boolean) {
        addView(text(label, size, color, bold).apply { gravity = Gravity.CENTER }, linearParams(bottom = 8.dp))
    }

    private fun LinearLayout.addCheckLine(label: String) {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val box = TextView(this@MainActivity).apply {
            text = ""
            background = rounded(Color.WHITE, 4.dp, border, 1.dp)
        }
        row.addView(box, linearParams(width = 18.dp, height = 18.dp, end = 8.dp))
        row.addView(text(label, 12f, muted), linearParams(width = 0, weight = 1f))
        addView(row, linearParams(bottom = 16.dp))
    }

    private fun LinearLayout.addDashboardRow(title: String, subtitle: String, destination: AppScreen) {
        addChoiceRow(title, subtitle, destination)
    }

    private fun LinearLayout.addCvRow(title: String, subtitle: String) {
        addChoiceRow(title, subtitle, AppScreen.Preview)
    }

    private fun LinearLayout.addChoiceCard(title: String, subtitle: String, action: () -> Unit) {
        val item = choiceRowView(title, subtitle)
        item.setOnClickListener { action() }
        addView(item, linearParams(bottom = 12.dp))
    }

    private fun LinearLayout.addChoiceRow(title: String, subtitle: String, destination: AppScreen? = null) {
        val item = choiceRowView(title, subtitle)
        destination?.let { screen -> item.setOnClickListener { navigate(screen) } }
        addView(item, linearParams(bottom = 10.dp))
    }

    private fun LinearLayout.addDangerRow(title: String, action: () -> Unit) {
        val item = choiceRowView(title, "")
        item.setOnClickListener { action() }
        val titleView = ((item.getChildAt(0) as LinearLayout).getChildAt(0) as TextView)
        titleView.setTextColor(danger)
        addView(item, linearParams(bottom = 10.dp))
    }

    private fun choiceRowView(title: String, subtitle: String): LinearLayout {
        val item = roundedCard()
        item.orientation = LinearLayout.HORIZONTAL
        item.gravity = Gravity.CENTER_VERTICAL
        item.setPadding(14.dp, 14.dp, 12.dp, 14.dp)
        val copy = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        copy.addView(text(title, 15f, ink, true))
        subtitle.takeIf { it.isNotBlank() }?.let {
            copy.addView(text(it, 12f, muted), linearParams(top = 3.dp))
        }
        item.addView(copy, linearParams(width = 0, weight = 1f))
        item.addView(text(">", 18f, muted, true), linearParams(start = 8.dp))
        return item
    }

    private fun LinearLayout.addEntryCard(title: String, lines: List<String>) {
        val item = roundedCard()
        item.setPadding(14.dp, 14.dp, 14.dp, 14.dp)
        val header = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        header.addView(text(title, 15f, ink, true), linearParams(width = 0, weight = 1f))
        header.addView(text("Edit", 12f, blue, true), linearParams(end = 8.dp))
        header.addView(text("Del", 12f, danger, true))
        item.addView(header, linearParams(bottom = 8.dp))
        lines.forEach { item.addView(text(it, 12f, muted), linearParams(top = 2.dp)) }
        addView(item, linearParams(bottom = 12.dp))
    }

    private fun LinearLayout.addLevelRow(title: String, level: String) {
        val row = roundedCard()
        row.orientation = LinearLayout.HORIZONTAL
        row.gravity = Gravity.CENTER_VERTICAL
        row.setPadding(14.dp, 14.dp, 14.dp, 14.dp)
        row.addView(text(title, 14f, ink, true), linearParams(width = 0, weight = 1f))
        row.addView(text(level, 12f, muted, true))
        addView(row, linearParams(bottom = 10.dp))
    }

    private fun LinearLayout.addSwitchRow(title: String, subtitle: String, checked: Boolean) {
        val row = roundedCard()
        row.orientation = LinearLayout.HORIZONTAL
        row.gravity = Gravity.CENTER_VERTICAL
        row.setPadding(14.dp, 14.dp, 10.dp, 14.dp)
        val copy = LinearLayout(this@MainActivity).apply { orientation = LinearLayout.VERTICAL }
        copy.addView(text(title, 15f, ink, true))
        subtitle.takeIf { it.isNotBlank() }?.let { copy.addView(text(it, 12f, muted), linearParams(top = 3.dp)) }
        row.addView(copy, linearParams(width = 0, weight = 1f))
        row.addView(SwitchCompat(this@MainActivity).apply {
            isChecked = checked
            buttonTintList = ColorStateList.valueOf(blue)
        })
        addView(row, linearParams(bottom = 10.dp))
    }

    private fun LinearLayout.addSectionTitle(title: String) {
        addView(text(title, 13f, muted, true), linearParams(top = 12.dp, bottom = 8.dp))
    }

    private fun LinearLayout.addAddLink(label: String) {
        addView(text(label, 13f, blue, true).apply {
            gravity = Gravity.CENTER
        }, linearParams(top = 8.dp, bottom = 18.dp))
    }

    private fun LinearLayout.addScoreLine(label: String, score: String) {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        row.addView(text(label, 14f, ink, true), linearParams(width = 0, weight = 1f))
        row.addView(text(score, 14f, success, true))
        addView(row, linearParams(top = 12.dp, bottom = 4.dp))
        val bar = FrameLayout(this@MainActivity).apply {
            background = rounded(Color.parseColor("#D9FBE8"), 999.dp)
        }
        val fill = View(this@MainActivity).apply {
            background = rounded(success, 999.dp)
        }
        bar.addView(fill, FrameLayout.LayoutParams(match, match))
        addView(bar, linearParams(height = 8.dp, bottom = 8.dp))
    }

    private fun LinearLayout.addTabHeader(first: String, second: String) {
        val tabs = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
        }
        tabs.addView(text(first, 14f, blue, true).apply { gravity = Gravity.CENTER }, linearParams(width = 0, weight = 1f))
        tabs.addView(text(second, 14f, muted, true).apply { gravity = Gravity.CENTER }, linearParams(width = 0, weight = 1f))
        addView(tabs, linearParams(bottom = 12.dp))
    }

    private fun cvPreviewSection(title: String, body: String): LinearLayout {
        val section = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 0, 0, 12.dp)
        }
        section.addView(text(title, 13f, blue, true), linearParams(bottom = 5.dp))
        section.addView(text(body, 11f, ink))
        return section
    }

    private fun LinearLayout.addPrimaryButton(label: String, action: () -> Unit) {
        addView(primaryButton(label, action), linearParams(top = 4.dp, bottom = 10.dp))
    }

    private fun LinearLayout.addSecondaryButton(label: String, action: () -> Unit) {
        addView(secondaryButton(label, action), linearParams(top = 4.dp, bottom = 10.dp))
    }

    private fun LinearLayout.addDangerButton(label: String, action: () -> Unit) {
        addView(primaryButton(label, action, danger), linearParams(top = 4.dp, bottom = 10.dp))
    }

    private fun LinearLayout.addButtonRow(
        left: String,
        right: String,
        leftAction: () -> Unit,
        rightAction: () -> Unit
    ) {
        val row = LinearLayout(this@MainActivity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        row.addView(secondaryButton(left, leftAction), linearParams(width = 0, weight = 1f, end = 8.dp))
        row.addView(primaryButton(right, rightAction), linearParams(width = 0, weight = 1f, start = 8.dp))
        addView(row, linearParams(top = 8.dp))
    }

    private fun primaryButton(label: String, action: () -> Unit, color: Int = blue): MaterialButton {
        return MaterialButton(this).apply {
            text = label
            isAllCaps = false
            textSize = 14f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE)
            cornerRadius = 10.dp
            minHeight = 48.dp
            backgroundTintList = ColorStateList.valueOf(color)
            setOnClickListener { action() }
        }
    }

    private fun secondaryButton(label: String, action: () -> Unit): MaterialButton {
        return MaterialButton(this).apply {
            text = label
            isAllCaps = false
            textSize = 14f
            setTextColor(blue)
            cornerRadius = 10.dp
            minHeight = 46.dp
            strokeWidth = 1.dp
            strokeColor = ColorStateList.valueOf(border)
            backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            setOnClickListener { action() }
        }
    }

    private fun bottomNavigation(selected: AppScreen): LinearLayout {
        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(8.dp, 8.dp, 8.dp, 8.dp)
            background = rounded(Color.WHITE, 0.dp, border, 1.dp)
        }
        nav.addView(navItem("Accueil", AppScreen.Dashboard, selected), linearParams(width = 0, weight = 1f))
        nav.addView(navItem("Mes CV", AppScreen.MyCvs, selected), linearParams(width = 0, weight = 1f))
        nav.addView(navItem("Importer", AppScreen.ImportCv, selected), linearParams(width = 0, weight = 1f))
        nav.addView(navItem("Profil", AppScreen.Profile, selected), linearParams(width = 0, weight = 1f))
        return nav
    }

    private fun navItem(label: String, screen: AppScreen, selected: AppScreen): TextView {
        val active = screen == selected
        return text(label, 11f, if (active) blue else muted, active).apply {
            gravity = Gravity.CENTER
            setPadding(4.dp, 9.dp, 4.dp, 9.dp)
            background = if (active) rounded(soft, 12.dp) else null
            setOnClickListener { navigateRoot(screen) }
        }
    }

    private fun chipRow(vararg labels: String): LinearLayout {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.START
        }
        labels.forEach { label ->
            row.addView(text(label, 13f, ink, true).apply {
                gravity = Gravity.CENTER
                setPadding(16.dp, 10.dp, 16.dp, 10.dp)
                background = rounded(Color.parseColor("#F1F5F9"), 12.dp)
            }, linearParams(end = 10.dp, bottom = 10.dp))
        }
        return row
    }

    private fun roundedCard(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(14.dp, 14.dp, 14.dp, 14.dp)
            background = rounded(card, 14.dp, border, 1.dp)
        }
    }

    private fun iconButton(label: String, action: () -> Unit): TextView {
        return text(label, 16f, ink, true).apply {
            gravity = Gravity.CENTER
            background = rounded(Color.WHITE, 999.dp, border, 1.dp)
            setOnClickListener { action() }
        }
    }

    private fun avatar(initials: String, sizeDp: Int): TextView {
        return text(initials, 18f, Color.WHITE, true).apply {
            gravity = Gravity.CENTER
            background = oval(blue, blueDark, 1.dp)
            layoutParams = ViewGroup.LayoutParams(sizeDp.dp, sizeDp.dp)
        }
    }

    private fun text(value: String, size: Float, color: Int, bold: Boolean = false): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            includeFontPadding = true
            if (bold) typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun LinearLayout.addTopSpace(dp: Int) {
        addView(View(this@MainActivity), linearParams(height = dp.dp))
    }

    private fun LinearLayout.addFlexibleSpace() {
        addView(View(this@MainActivity), linearParams(height = 20.dp, weight = 1f))
    }

    private fun rounded(
        fill: Int,
        radius: Int,
        strokeColor: Int? = null,
        strokeWidth: Int = 0
    ): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(fill)
            cornerRadius = radius.toFloat()
            if (strokeColor != null && strokeWidth > 0) {
                setStroke(strokeWidth, strokeColor)
            }
        }
    }

    private fun oval(fill: Int, strokeColor: Int, strokeWidth: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(fill)
            setStroke(strokeWidth, strokeColor)
        }
    }

    private fun linearParams(
        width: Int = match,
        height: Int = wrap,
        start: Int = 0,
        top: Int = 0,
        end: Int = 0,
        bottom: Int = 0,
        weight: Float = 0f
    ): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(width, height, weight).apply {
            setMargins(start, top, end, bottom)
        }
    }

    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).roundToInt()

    private enum class AppScreen {
        Login,
        Register,
        ForgotPassword,
        Dashboard,
        MyCvs,
        CreateCv,
        PersonalInfo,
        Summary,
        Experiences,
        Education,
        Skills,
        Languages,
        Certifications,
        Projects,
        Achievements,
        Interests,
        References,
        Preview,
        Score,
        ImportCv,
        Profile,
        Settings,
        Notifications,
        Confidentiality,
        HelpCenter,
        Faq,
        ContactSupport,
        Terms,
        PrivacyPolicy,
        DeleteAccount
    }

    private companion object {
        const val match = ViewGroup.LayoutParams.MATCH_PARENT
        const val wrap = ViewGroup.LayoutParams.WRAP_CONTENT
    }
}
