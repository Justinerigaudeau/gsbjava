package view;

import gestion.Ctrl;

/**
 * Interface MyView permettant � toutes les vues de l'application d'impl�menter la m�thode contenue dans celle-ci
 * @author xavier
 *
 */
public interface MyView {
	
	/**
	 * M�thode abstraite (sans corps) donc � r�d�finir dans toutes les classes filles.
	 * Cette m�thode a pour objectif de d�finir un observateur sur la vue fille.
	 * Cet observateur sera une instance de la classe Ctrl
	 * @param ctrl L'observateur de la future vue
	 */
	public abstract void assignListener(Ctrl ctrl);

}
